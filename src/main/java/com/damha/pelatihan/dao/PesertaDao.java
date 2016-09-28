/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damha.pelatihan.dao;

import com.damha.pelatihan.entity.Peserta;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PesertaDao extends PagingAndSortingRepository<Peserta, String>{
    
}
