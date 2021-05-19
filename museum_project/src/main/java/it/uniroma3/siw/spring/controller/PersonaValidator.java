package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Persona;
import it.uniroma3.siw.spring.service.PersonaService;

public class PersonaValidator implements Validator {

	@Autowired
	private PersonaService personaService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Persona.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome","required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome","required");
		if(this.personaService.alreadyExists((Persona) target)) {
			errors.reject("duplicato");
		}
	}

	
}
