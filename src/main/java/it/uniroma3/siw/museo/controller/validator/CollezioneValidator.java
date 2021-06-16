package it.uniroma3.siw.museo.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.service.CollezioneService;

@Component
public class CollezioneValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(CollezioneValidator.class);
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Collezione.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		
		if(!errors.hasErrors()) {
			logger.debug("logger: nome della collezione valido");
			if(this.collezioneService.exists((Collezione)o)) {
				logger.debug("logger: esiste già nel DB una collezione con questo nome");
				errors.reject("duplicato");
			}
		}
	}
}