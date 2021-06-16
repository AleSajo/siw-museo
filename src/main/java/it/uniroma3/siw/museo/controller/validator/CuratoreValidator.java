package it.uniroma3.siw.museo.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.museo.model.Curatore;
import it.uniroma3.siw.museo.service.CuratoreService;

@Component
public class CuratoreValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(CollezioneValidator.class);
	
    @Autowired
    private CuratoreService curatoreService;
    
	@Override
	public boolean supports(Class<?> clazz) {
		return Curatore.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
	}
	//TODO bisogna inserire la possibilità di scegliere un curatore già esistente
	// potrei fare che se il curatore esiste allora viene restituito e aggiunto
	// alla collezione
}
