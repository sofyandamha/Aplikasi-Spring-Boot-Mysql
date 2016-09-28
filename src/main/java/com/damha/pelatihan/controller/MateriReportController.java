/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damha.pelatihan.controller;

import com.damha.pelatihan.dao.MateriDao;
import com.damha.pelatihan.entity.Materi;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MateriReportController {

    @Autowired
    private MateriDao md;

    @RequestMapping("/materi")
    public ModelAndView generateReportMateri(ModelAndView m) {
        Iterable<Materi> data = md.findAll();
        m.addObject("dataSource", data);
        m.addObject("tanggalUpdate", new Date());
        m.addObject("format", "pdf");
        m.setViewName("report_materi");
        return m;
    }
}
