package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.Persona;
import it.uniroma3.siw.spring.service.PersonaService;

@Controller
public class PersonaController {

	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private PersonaValidator personaValidator;
	
	// risponde a richiesta GET con URL: /addPersona
	@RequestMapping(value="/persona",method=RequestMethod.POST)
	public String newPersona(/*@Valid*/@ModelAttribute("persona") Persona persona,Model model,BindingResult bindingResult) {
		this.personaValidator.validate(persona,bindingResult);
		if(!bindingResult.hasErrors()) {        // se i dati sono corretti
			this.personaService.save(persona);  // salvo un oggetto persona
			model.addAttribute("persone",this.personaService.findAll()); 
			return "persone.html";              // presenta una pagina con l'elenco di tutte le persone
		}
		else {
			return "personaForm.html";          // ci sono errori, torna alla form iniziale
		}
	}
	
	// risponde a richiesta GET con URL del tipo: /persona/1234
	@RequestMapping(value="/persona/{id}",method=RequestMethod.GET)
	public String getPersona(@PathVariable("id") Long id,Model model) {
		model.addAttribute("persona",this.personaService.findById(id));
		return "persona.html";
	}
}
