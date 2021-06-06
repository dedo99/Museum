package it.uniroma3.siw.spring.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Credenziali;
import it.uniroma3.siw.spring.service.CredenzialiService;

@Component
public class CredenzialiValidator implements Validator {

	@Autowired
	private CredenzialiService credenzialiService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Credenziali.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "username", "required");
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		
		if(!errors.hasErrors()) {
			if(this.credenzialiService.alreadyExistsByUsername((Credenziali) target)) {
				errors.reject("amministratoreNonEsiste");
			}
		}
	}

}
