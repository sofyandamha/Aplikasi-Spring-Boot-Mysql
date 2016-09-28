/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damha.pelatihan.controller;

import com.damha.pelatihan.dao.PesertaDao;
import com.damha.pelatihan.entity.Peserta;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/peserta")
public class PesertaHtmlController {

    @Autowired
    private PesertaDao pd;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/haloRest")
    @ResponseBody
    public Map<String, Object> halo(@RequestParam("nama") String nama) {
        Map<String, Object> hasil = new HashMap<>();
        hasil.put("nama", nama);
        hasil.put("waktu", new Date());

        return hasil;
    }

    @RequestMapping("/list")
    public void daftarPeserta(Model m) {
        m.addAttribute("daftarPeserta", pd.findAll());

    }

    @RequestMapping("/hapus")
    public String hapus(@RequestParam("id") String id) {
        pd.delete(id);
        return "redirect:list";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String tampilform(@RequestParam(value = "id", required = false) String id, Model m) {
        
        m.addAttribute("peserta", new Peserta());
        if (id != null && !id.isEmpty()){
            Peserta p = pd.findOne(id);
            m.addAttribute("peserta", p);
        }
        return "/peserta/form";
    }
    
    
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String prosesform(@Valid Peserta p, BindingResult errors) {
        if (errors.hasErrors()) {
            return "/peserta/form";
        }
        pd.save(p);
        return "redirect:list";
    }
    
     @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String tampiladdform(Peserta peserta) {
        
        return "/peserta/add";
    }
    
    
   
    
    
}
