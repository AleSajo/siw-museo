package it.uniroma3.siw.museo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.museo.controller.validator.CollezioneValidator;
import it.uniroma3.siw.museo.controller.validator.CuratoreValidator;
import it.uniroma3.siw.museo.model.Artista;
import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Opera;
import it.uniroma3.siw.museo.service.CollezioneService;

@Controller
public class CollezioneController {
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private CollezioneService collezioneService;
    
    @Autowired
    private CollezioneValidator collezioneValidator;
    
    @Autowired
    private CuratoreValidator curatoreValidator;
    
    //variabile temporanea che contiene una collezione temporanea
    //per memorizzarla durante la conferma dei dati
    private Collezione collezioneTemp;
    
	@RequestMapping(value="/collezione/{id}", method=RequestMethod.GET)
	public String mostraCollezione(@PathVariable("id") Long id, Model model) {
		model.addAttribute("collezione", this.collezioneService.collezionePerId(id));
		return "collezione";
	}
    
    @RequestMapping(value = "/inserisciCollezione",method = RequestMethod.GET)
    public String showInserisciCollezione(Model model) {
    	logger.debug("logger: istanzio una Collezione e passo alla vista successiva");
    	model.addAttribute("nuovaCollezione", new Collezione());
    	return "admin/inserisciCollezione";
    }
    
    @RequestMapping(value = "/inviaDatiCollezione",method = RequestMethod.POST)
    public String prendiDatiCollezione(
    		@ModelAttribute("nuovaCollezione") Collezione nuovaCollezione,
    		Model model,
    		BindingResult bindingResult) {
    	
    	this.collezioneValidator.validate(nuovaCollezione, bindingResult);
    	if(!bindingResult.hasErrors()) {
    		logger.debug("logger: passo alla conferma");
    		collezioneTemp=nuovaCollezione;
    		model.addAttribute("nuovaCollezione", collezioneTemp);
    		return "admin/confermaInserisciCollezione";
    	} else {
    		return "admin/inserisciCollezione";
    	}
    	
    }
    
    /*prende la collezione, la salva nel db, poi mostra la pagina adminHome
     * con l'elenco delle collezioni*/
    @RequestMapping(value = "/confermaDatiCollezione",method = RequestMethod.GET)
    public String confermaDatiCollezione(
    		Model model) {
    	logger.debug("logger: confermo e salvo nel DB la collezione");
    	logger.debug(collezioneTemp.toString());
    	this.collezioneService.inserisci(collezioneTemp);
    	logger.debug("logger: collezione inserita nel DB");
    	model.addAttribute("collezioni", this.collezioneService.tutte());
    	return "admin/adminHome";
    }
    
    @RequestMapping(value="/modificaCollezione/{id}",method=RequestMethod.GET)
    public String mostraModificaCollezione(@PathVariable("id") Long id, Model model) {
    	logger.debug("logger: mostraModificaCollezione");
    	model.addAttribute("collezione", this.collezioneService.collezionePerId(id));
    	model.addAttribute("nuovaOpera", new Opera());
    	model.addAttribute("nuovoArtista", new Artista());
    	return "admin/modificaCollezione";
    }
    
    @RequestMapping(value="/eliminaCollezione/{id}",method=RequestMethod.GET)
    public String mostraConfermaElimina(@PathVariable("id") Long id, Model model) {
    	logger.debug("logger: mostraConfermaElimina");
    	collezioneTemp = this.collezioneService.collezionePerId(id);
    	model.addAttribute("collezione", collezioneTemp);
    	return "admin/confermaEliminaCollezione";
    }
    
    @RequestMapping(value="/confermaEliminaCollezione",method=RequestMethod.GET)
    public String confermaEliminaCollezione(Model model) {
    	logger.debug("logger: confermaEliminaCollezione");
    	this.collezioneService.rimuovi(collezioneTemp);
    	model.addAttribute("collezioni", this.collezioneService.tutte());
    	return "admin/adminHome";
    }
}
