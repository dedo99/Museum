package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class CollezioneController {
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private OperaService operaService;
	
	@RequestMapping(value = "/collezioni", method = RequestMethod.GET)
	public String getCollezioni(Model model) {
		model.addAttribute("collezioni", this.collezioneService.tutteCollezioni());
		return "collezioni.html";
	}
	
	@RequestMapping(value = "/collezione/{nome}", method = RequestMethod.GET)
    public String getCollezione(@PathVariable("nome") String nome, Model model) {
    	model.addAttribute("collezione", this.collezioneService.collezionePerId(nome));
    	Collezione c = this.collezioneService.collezionePerId(nome);
    	model.addAttribute("opere",this.operaService.findOpereByCollezione(c));
    	return "collezione.html";
    }

}
