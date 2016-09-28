/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damha.pelatihan.dao;


import com.damha.pelatihan.AplikasiTutorialSpringApplication;
import com.damha.pelatihan.entity.Peserta;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.Date;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.Assert;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AplikasiTutorialSpringApplication.class)
@Sql (executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = "/data/peserta.sql"
        )
public class PesertaDaoTest {
    
    @Autowired
    private PesertaDao pd;
    
    @Autowired
    private DataSource ds;
  
    
    
    @Test
    public void testInsert() throws SQLException{
        
        Peserta p = new Peserta();
        p.setNama("Ahmad Sofyan");
        p.setEmail("sofyan@incubatechnology.com");
        p.setTanggalLahir(new Date());
       pd.save(p);
     
       String sql = "select count(*) as jumlah " 
                    + "from peserta "
                    + "where email = 'sofyan@incubatechnology.com'";
        
             Connection c = ds.getConnection();
            ResultSet rs = c.createStatement().executeQuery(sql);
           Assert.assertTrue(rs.next());
            
            Long jumlahRow = rs.getLong("jumlah");
            Assert.assertEquals(1L, jumlahRow.longValue());
          
    }
    @Test
    public void Hitung(){
        Long jumlah = pd.count();
        Assert.assertEquals(3L, jumlah.longValue());
        
    }
    @Test
    public void cariById(){
        Peserta p = pd.findOne("A001");
        Assert.assertNotNull(p);
        Assert.assertEquals("Sofyan Jalil", p.getNama());
        Assert.assertEquals("jalil@gmail.com", p.getEmail());
    }
    
    @After
    public void Hapusdata() throws Exception{
        String sql = "delete from peserta where email='sofyan@incubatechnology.com'";
        Connection c = ds.getConnection();
        c.createStatement().executeUpdate(sql);
        
    }
    
     
}

