/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damha.pelatihan.dao;

import com.damha.pelatihan.entity.Materi;
import com.damha.pelatihan.entity.Sesi;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface SesiDao extends PagingAndSortingRepository<Sesi, String>{
    public Page<Sesi> findByMateri (Materi m, Pageable page);
    
    @Query("select x from Sesi x where x.mulai >= :m and x.mulai < :s and x.materi.kode = :k order by x.materi desc")
    public Page<Sesi> cariTglMulaidanKodeMateri(
            @Param("m")Date mulai,
            @Param("s")  Date sampai,
            @Param("k") String kode,
            Pageable page);
    

}
