package it.uniroma3.siw.spring.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.CuratoreService;

@Component
public class CollezioneValidator implements Validator {

	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private CuratoreService curatoreService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Collezione.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Collezione collezione = (Collezione) target;
		ValidationUtils.rejectIfEmpty(errors, "nome","required");
		ValidationUtils.rejectIfEmpty(errors, "descrizione","required");
		ValidationUtils.rejectIfEmpty(errors, "curatore","required");
		
		if(!errors.hasErrors()) {
			if(this.collezioneService.alreadyExists(collezione)) {
				errors.reject("collezioneDuplicato");
			}
			if(this.curatoreService.alreadyExists(collezione.getCuratore())) {
				errors.rejectValue("curatore","curatoreNonEsiste");
			}
		}
		
	}
	

}
