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

import com.sun.org.apache.bcel.internal.generic.NEW;

import it.uniroma3.siw.museo.controller.validator.CollezioneValidator;
import it.uniroma3.siw.museo.model.Artista;
import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Opera;
import it.uniroma3.siw.museo.service.ArtistaService;
import it.uniroma3.siw.museo.service.CollezioneService;
import it.uniroma3.siw.museo.service.OperaService;

@Controller
public class OperaController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private OperaService operaService;
    
    @Autowired
    private CollezioneService collezioneService;
    
    @Autowired
    private ArtistaService artistaService;
    
    //TODO mettere il validator dell'opera
    
    /* prendo i dettagli dell'opera e li metto in un oggetto opera
     * poi dovrei mettere quest'opera dentro la collezione che
     * stiamo visualizzando nella view
     * */
    @RequestMapping(value="/aggiungiOpera/{idCollezione}",method=RequestMethod.POST)
    public String aggiungiOpera(
    		@ModelAttribute("nuovaOpera") Opera nuovaOpera,
    		@ModelAttribute("nuovoArtista") Artista nuovoArtista,
    		@PathVariable("idCollezione") Long id,
    		Model model,
    		BindingResult bindingResult) {
    	
    	logger.debug("logger: operaController.aggiungOpera");
    	
    	if(artistaService.exists(nuovoArtista.getNome(),nuovoArtista.getCognome())) {
    		nuovoArtista = artistaService.artistaPerNomeECognome(nuovoArtista.getNome(), nuovoArtista.getCognome());
    	}
    	
    	nuovaOpera.setArtista(nuovoArtista);
    	nuovoArtista.getOpere().add(nuovaOpera);
    	Collezione collezione = this.collezioneService.collezionePerId(id);
    	collezione.getOpere().add(nuovaOpera);
    	nuovaOpera.setCollezione(collezione);
    	operaService.inserisci(nuovaOpera);
    	
    	model.addAttribute("collezione",this.collezioneService.collezionePerId(id));
    	model.addAttribute("nuovaOpera", new Opera());
    	model.addAttribute("nuovoArtista", new Artista());
    	return "admin/modificaCollezione";
    }
    
    @RequestMapping(value="/opera/{id}",method=RequestMethod.GET)
    public String mostraOpera(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("opera", this.operaService.operaPerId(id));
    	return "opera";
    }
    
    @RequestMapping(value="/eliminaOpera/{id}",method=RequestMethod.GET)
    public String eliminaOpera(@PathVariable("id") Long id, Model model) {
    	Collezione collezione = operaService.operaPerId(id).getCollezione();
    	collezione.getOpere().remove(operaService.operaPerId(id));
    	collezioneService.inserisci(collezione);
    	operaService.rimuovi(operaService.operaPerId(id));

    	model.addAttribute("collezione", collezioneService.collezionePerId(collezione.getId()));
    	model.addAttribute("nuovaOpera", new Opera());
    	model.addAttribute("nuovoArtista", new Artista());
    	return "admin/modificaCollezione";
    }
}
