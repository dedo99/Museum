package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.controller.validator.CollezioneValidator;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class CollezioneController {
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private CollezioneValidator collezioneValidator;
	
	@Autowired
	private OperaService operaService;
	
	@RequestMapping(value = "/collezioni", method = RequestMethod.GET)
	public String getCollezioni(Model model) {
		model.addAttribute("collezioni", this.collezioneService.tutteCollezioni());
		return "collezioni.html";
	}
	
	@RequestMapping(value = "/collezione/{nome}", method = RequestMethod.GET)
    public String getCollezione(@PathVariable("nome") String nome, Model model) {
		Collezione c = this.collezioneService.collezionePerId(nome);
    	model.addAttribute("collezione", c);
    	model.addAttribute("opere",this.operaService.findOpereByCollezione(c));
    	return "collezione.html";
    }
	
	@RequestMapping(value = "/admin/insertCollezione", method = RequestMethod.GET)
	public String visualizzaInserisciCollezione(Model model) {
		model.addAttribute("collezione",new Collezione());
		model.addAttribute("collezioni", this.collezioneService.tutteCollezioni());
		return "admin/inserisci_collezione_amm.html";
	}
	
	@RequestMapping(value = "/admin/addCollezione", method = RequestMethod.POST)
    public String saveCollezione(@ModelAttribute("collezione") Collezione collezione,
    		@RequestParam("file") MultipartFile file,
    		Model model,
    		BindingResult bindingResult)
    {
		this.collezioneValidator.validate(collezione, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.collezioneService.saveCollezioneToDB(file,collezione);
			model.addAttribute("collezione",new Collezione());
		}
		model.addAttribute("collezioni", this.collezioneService.tutteCollezioni());
    	return "admin/inserisci_collezione_amm.html";
    }
	
	@RequestMapping(value = "/admin/deleteCollezione", method = RequestMethod.GET)
	public String visualizzaCancellaCollezione(Model model) {
		model.addAttribute("collezioni", this.collezioneService.tutteCollezioni());
		return "admin/cancella_collezione_amm.html";
	}
	
	@RequestMapping(value = "/admin/deleteCollezione", method = RequestMethod.POST)
	public String visualizzaCancellaCollezione(Model model, @RequestParam("nome") String nome) {
		Collezione c = this.collezioneService.collezionePerId(nome);
		this.collezioneService.deleteCollezione(c);
		model.addAttribute("collezioni", this.collezioneService.tutteCollezioni());
		return "admin/cancella_collezione_amm.html";
	}
}
