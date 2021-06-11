package it.uniroma3.siw.spring.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.OperaService;

@Component
public class OperaValidator implements Validator{

	@Autowired
	private OperaService operaService;
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private ArtistaService artistaService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Opera.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Opera opera = (Opera) target;
		ValidationUtils.rejectIfEmpty(errors, "titolo","required");
		ValidationUtils.rejectIfEmpty(errors, "annoDiRealizzazione","required");
		ValidationUtils.rejectIfEmpty(errors, "descrizione","required");
		ValidationUtils.rejectIfEmpty(errors, "collezione","required");
		ValidationUtils.rejectIfEmpty(errors, "artista","required");
		
		if(!errors.hasErrors()) {
			if(this.operaService.alreadyExists(opera)) {
				errors.reject("operaDuplicato");
			}
//			if(this.collezioneService.alreadyExists(opera.getCollezione())) {
//				errors.rejectValue("collezione","collezioneNonEsiste");
//			}
//			if(this.artistaService.alreadyExists(opera.getArtista())) {
//				errors.rejectValue("artista","artistaNonEsiste");
//			}
			
		}
		
	}
}
