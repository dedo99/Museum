package it.uniroma3.siw.spring.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.service.ArtistaService;

@Component
public class ArtistaValidator implements Validator{
	
	@Autowired
	private ArtistaService artistaService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Artista.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Artista artista = (Artista) target;
		ValidationUtils.rejectIfEmpty(errors, "nome","required");
		ValidationUtils.rejectIfEmpty(errors, "cognome","required");
		ValidationUtils.rejectIfEmpty(errors, "dataDiNascita","required");
		ValidationUtils.rejectIfEmpty(errors, "luogoDiNascita","required");
		ValidationUtils.rejectIfEmpty(errors, "nazionalita","required");
		ValidationUtils.rejectIfEmpty(errors, "biografia","required");
		
		if(!errors.hasErrors()) {
			if(this.artistaService.alreadyExistsByNomeAndCognome(artista)) {
				errors.reject("artistaDuplicato");
			}	
		}	
	}
}
