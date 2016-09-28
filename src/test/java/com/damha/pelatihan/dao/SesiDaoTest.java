/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damha.pelatihan.dao;


import com.damha.pelatihan.AplikasiTutorialSpringApplication;
import com.damha.pelatihan.entity.Materi;
import com.damha.pelatihan.entity.Peserta;
import com.damha.pelatihan.entity.Sesi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.sql.DataSource;
import org.junit.Assert;




import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AplikasiTutorialSpringApplication.class)
@Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"/data/peserta.sql","/data/materi.sql","/data/sesi.sql"} 
        )
public class SesiDaoTest {
    
    @Autowired
    private SesiDao sd;
    
    @Autowired
    private DataSource ds;
    
    @Test
    public void cariByMateri(){
        Materi m = new Materi();
        m.setId("aa6");
        
        PageRequest page = new PageRequest(0, 5);
        Page<Sesi> hasilQuery = sd.findByMateri(m,page);
        Assert.assertEquals(2L, hasilQuery.getNumberOfElements());
        
       // Assert.assertFalse(hasilQuery.getContent().isEmpty());
        Sesi s = hasilQuery.getContent().get(0);
        Assert.assertNotNull(s);
        Assert.assertEquals("Belajar Java Fundamental", s.getMateri().getNama());
    }
    
    @Test
    public void testcariTglMulaidanKodeMateri() throws Exception{
            PageRequest page = new PageRequest(0, 5);
            
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date sejak = formatter.parse("2016-01-02");
        Date sampai = formatter.parse("2016-01-16");
     
        Page<Sesi> hasil = sd.cariTglMulaidanKodeMateri(
                sejak, sampai, "BJF-001", page);
        Assert.assertEquals(2L, hasil.getTotalElements());
        
        Sesi s = hasil.getContent().get(0);
        Assert.assertEquals("Belajar Java Fundamental", s.getMateri().getNama());
    }
    
    @Test
    public void testSaveSesi() throws Exception{
        
        Peserta p = new Peserta();
        p.setId("A001");
        
        Peserta p1 = new Peserta();
        p1.setId("A003");
        
        Materi m = new Materi();
        m.setId("aa8");
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date sejak = formatter.parse("2016-02-02");
        Date sampai = formatter.parse("2016-02-16");
        
        Sesi s = new Sesi();
        s.setMateri(m);
        s.setMulai(sejak);
        s.setSampai(sampai);
        s.getDaftarpeserta().add(p);
        s.getDaftarpeserta().add(p1);
        
        sd.save(s);
        String idSesiBaru = s.getId();
        Assert.assertNotNull(idSesiBaru);
        System.out.println("ID Baru Sesi"+s.getId());
        
        
        String sql="select count(*) from sesi where id_materi='aa8'";
        String sqlManytoMany = "select count(*) from peserta_pelatihan  "
                + "where id_sesi=?";
        
        Connection c = ds.getConnection();
        ResultSet rs = c.createStatement().executeQuery(sql);
        Assert.assertTrue(rs.next());
        Assert.assertEquals(1L, rs.getLong(1));
        
        PreparedStatement ps = c.prepareStatement(sqlManytoMany);
        ps.setString(1, idSesiBaru);
        ResultSet rs2 = ps.executeQuery();
        Assert.assertTrue(rs2.next());
        Assert.assertEquals(2L, rs2.getLong(1));
        
    }
}




