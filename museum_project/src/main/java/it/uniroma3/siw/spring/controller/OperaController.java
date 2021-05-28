package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class OperaController {
	
	@Autowired
	private OperaService operaService;
	
	@RequestMapping(value = "/opera/{titolo}", method = RequestMethod.GET)
    public String getCollezione(@PathVariable("titolo") String titolo, Model model) {
    	model.addAttribute("opera", this.operaService.operaPerId(titolo));
    	return "opera.html";
    }
	
	@RequestMapping(value = "/insertOpera", method = RequestMethod.GET)
	public String visualizzaInserisciOpera(Model model) {
		model.addAttribute("opere", this.operaService.findAllOpera());
		return "inserisci_opera_amm.html";
	}
	
	@RequestMapping(value = "/addOpera", method = RequestMethod.POST)
    public String saveOpera(@RequestParam("file") MultipartFile file,
    		@RequestParam("titolo") String titolo,
    		@RequestParam("descrizione") String descrizione,
    		@RequestParam("anno") Integer anno,
    		@RequestParam("artista") Artista artista,
    		@RequestParam("collezione") Collezione collezione,
    		Model model)
    {
		this.operaService.saveOperaToDB(file, titolo, descrizione, anno, collezione, artista);
		model.addAttribute("opere", this.operaService.findAllOpera());
    	return "inserisci_opera_amm.html";
    }
	
	
	@RequestMapping(value = "/deleteOpera", method = RequestMethod.GET)
	public String visualizzaCancellaOpera(Model model) {
		return "cancella_opera_amm.html";
	}
	
	

}
