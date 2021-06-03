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

import it.uniroma3.siw.spring.controller.validator.ArtistaValidator;
import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class ArtistaController {
	
	@Autowired
	private ArtistaService artistaService;
	
	@Autowired
	private OperaService operaService;
	
	@Autowired
	private ArtistaValidator artistaValidator;
	
	@RequestMapping(value = "/artisti", method = RequestMethod.GET)
	public String getArtisti(Model model) {
		model.addAttribute("artisti", this.artistaService.tuttiArtisti());
		return "artisti.html";
	}
	
	@RequestMapping(value = "/artista/{id}", method = RequestMethod.GET) 
	public String getArtista(@PathVariable("id") Long id, Model model) {
		Artista a = this.artistaService.artistaPerId(id);
		model.addAttribute("artista",a);
		model.addAttribute("opere",this.operaService.findOpereByArtista(a));
		return "artista.html";
	}
	
	@RequestMapping(value = "/admin/insertArtista", method = RequestMethod.GET)
	public String visualizzaInserisciArtista(Model model) {
		model.addAttribute("artista",new Artista());
		model.addAttribute("artisti", this.artistaService.tuttiArtisti());
		return "admin/inserisci_artista_amm.html";
	}
	
	@RequestMapping(value = "/admin/addArtista", method = RequestMethod.POST)
    public String saveArtista(@RequestParam("file") MultipartFile file,
    		@ModelAttribute("artista") Artista artista,
    		Model model,
    		BindingResult bindingResult)
    {
		this.artistaValidator.validate(artista, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.artistaService.saveArtistaToDB(file, artista);
			model.addAttribute("artista",new Artista());
		}
		model.addAttribute("artisti", this.artistaService.tuttiArtisti());
    	return "admin/inserisci_artista_amm.html";
    }
	
	@RequestMapping(value = "/admin/deleteArtista", method = RequestMethod.GET)
	public String visualizzaCancellaArtista(Model model) {
		model.addAttribute("artisti", this.artistaService.tuttiArtisti());
		return "admin/cancella_artista_amm.html";
	}
	
	@RequestMapping(value = "/admin/deleteArtista", method = RequestMethod.POST)
	public String visualizzaCancellaArtista(Model model, @RequestParam("id") Long id) {
		Artista a = this.artistaService.artistaPerId(id);
		this.artistaService.cancellaArtista(a);
		model.addAttribute("artisti", this.artistaService.tuttiArtisti());
		return "admin/cancella_artista_amm.html";
	}
}
