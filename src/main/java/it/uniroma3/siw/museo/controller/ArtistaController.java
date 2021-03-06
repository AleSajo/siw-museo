package it.uniroma3.siw.museo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.museo.service.ArtistaService;

@Controller
public class ArtistaController {
	
private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private ArtistaService artistaService;
    
    @RequestMapping(value="/artista/{id}",method=RequestMethod.GET)
    public String mostraArtista(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("artista", this.artistaService.artistaPerId(id));
    	return "artista";
    }
}
