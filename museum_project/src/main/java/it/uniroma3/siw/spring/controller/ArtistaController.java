package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class ArtistaController {
	
	@Autowired
	private ArtistaService artistaService;
	
	@Autowired
	private OperaService operaService;
	
	@RequestMapping(value = "/artisti", method = RequestMethod.GET)
	public String getArtisti(Model model) {
		model.addAttribute("artisti", this.artistaService.tuttiArtisti());
		return "artisti.html";
	}
	
	@RequestMapping(value = "/artista/{nome}", method = RequestMethod.GET) 
	public String getArtista(@PathVariable("nome") String nome, Model model) {
		model.addAttribute("artista",this.artistaService.artistaPerId(nome));
		Artista a = this.artistaService.artistaPerId(nome);
		model.addAttribute("opere",this.operaService.findOpereByArtista(a));
		return "artista.html";
	}
	

}
