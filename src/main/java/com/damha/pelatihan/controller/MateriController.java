/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damha.pelatihan.controller;

import com.damha.pelatihan.dao.MateriDao;
import com.damha.pelatihan.entity.Materi;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MateriController  {
          @Autowired private MateriDao md;
        
        @RequestMapping(value = "/materi", method = RequestMethod.GET)
        public Page<Materi> daftarMateri(Pageable page){
            return md.findAll(page);
        }
        
        @RequestMapping(value = "/materi/{id}", method = RequestMethod.DELETE)
        @ResponseStatus(HttpStatus.OK)
        public void HapusMateri(@PathVariable("id")String id){
            md.delete(id);
        }
    
        @RequestMapping(value = "/materi", method = RequestMethod.POST)
        @ResponseStatus(HttpStatus.CREATED)
        public void insertMateri(@RequestBody @Valid Materi m){
            md.save(m);
        }
            
}
