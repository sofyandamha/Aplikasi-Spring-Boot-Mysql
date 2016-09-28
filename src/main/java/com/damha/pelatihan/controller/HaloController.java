
package com.damha.pelatihan.controller;

import com.damha.pelatihan.dao.PesertaDao;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HaloController {
    
   @Autowired private PesertaDao pd;
   
   @RequestMapping ("/halo")
    public void haloHtml(@RequestParam(value="nama", required = false) String nama,Model hasil){
       
        hasil.addAttribute("nama", nama);
        hasil.addAttribute("waktu",new Date());
        
    } 
    
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    
     @RequestMapping("/test")
    public void daftarPeserta(Model m){
        m.addAttribute("daftarPeserta", pd.findAll());
        
    }

}
