package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Curatore;
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
	
	@RequestMapping(value = "/addCollezione", method = RequestMethod.POST)
    public String saveCollezione(@RequestParam("file") MultipartFile file,
    		@RequestParam("nome") String nome,
    		@RequestParam("descrizione") String descrizione,
    		@RequestParam("curatore") Curatore curatore,
    		Model model)
    {
		this.collezioneService.saveCollezioneToDB(file, nome, descrizione, curatore);
		model.addAttribute("collezioni", this.collezioneService.tutteCollezioni());
    	return "inserisci_collezione_amm.html";
    }

}
