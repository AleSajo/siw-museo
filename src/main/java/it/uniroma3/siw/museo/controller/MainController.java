package it.uniroma3.siw.museo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.museo.model.Artista;
import it.uniroma3.siw.museo.service.ArtistaService;
import it.uniroma3.siw.museo.service.CollezioneService;
import it.uniroma3.siw.museo.service.CuratoreService;
import it.uniroma3.siw.museo.service.OperaService;

@Controller
public class MainController {
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private OperaService operaService;
	
	@Autowired
	private ArtistaService artistaService;
	
	@Autowired
	private CuratoreService curatoreService;
	
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public String home(Model model) {	//prima era index
			return "home";
	}
	
	@RequestMapping(value = {"/admin/adminHome"}, method = RequestMethod.GET)
	public String adminHome(Model model) {
		model.addAttribute("collezioni",collezioneService.tutte());
		return "admin/adminHome";
	}
	
	@RequestMapping(value = "/collezioni", method=RequestMethod.GET)
	public String mostraElencoCollezioni(Model model) {
		model.addAttribute("collezioni", this.collezioneService.tutteOrdineAlfabetico());
		return "collezioni";
	}
	
	@RequestMapping(value="/curatori",method=RequestMethod.GET)
	public String curatori(Model model) {
		model.addAttribute("curatori", curatoreService.tutti());
		return "curatori";
	}
	
	@RequestMapping(value="/opere",method=RequestMethod.GET)
	public String opere(Model model) {
		model.addAttribute("opere", operaService.tutte());
		return "opere";
	}
	
	@RequestMapping(value="/artisti",method=RequestMethod.GET)
	public String artisti(Model model) {
		model.addAttribute("artisti", artistaService.tutti());
		return "artisti";
	}
	
	@RequestMapping(value="/informazioni",method=RequestMethod.GET)
	public String informazioni(Model model) {
		return "informazioni";
	}
	
	@RequestMapping(value="/cerca",method=RequestMethod.POST)
	public String cerca(@RequestParam(value="cerca",required=false) String cerca, Model model) {
		logger.debug("logger: mainController.cerca");
		logger.debug(cerca);
		
		List<Artista> risultatiArtista = artistaService.artistiPerNome(cerca);
		risultatiArtista.addAll((List<Artista>)artistaService.artistiPerCognome(cerca));
		
		model.addAttribute("opere", this.operaService.operePerTitolo(cerca));
		model.addAttribute("collezione", this.collezioneService.collezionePerNome(cerca));
		model.addAttribute("artisti", risultatiArtista);
		return "risultatiRicerca";
	}
}