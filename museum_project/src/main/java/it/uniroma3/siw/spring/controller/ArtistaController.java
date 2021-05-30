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
	
	@RequestMapping(value = "/insertArtista", method = RequestMethod.GET)
	public String visualizzaInserisciArtista(Model model) {
		model.addAttribute("artisti", this.artistaService.tuttiArtisti());
		return "inserisci_artista_amm.html";
	}
	
	
	@RequestMapping(value = "/addArtista", method = RequestMethod.POST)
    public String saveArtista(@RequestParam("file") MultipartFile file,
    		@RequestParam("nome") String nome,
    		@RequestParam("cognome") String cognome,
    		@RequestParam("dataDiNascita") String dataDiNascita,
    		@RequestParam("dataDiMorte") String dataDiMorte,
    		@RequestParam("luogoDiNascita") String luogoDiNascita,
    		@RequestParam("luogoDiMorte") String luogoDiMorte,
    		@RequestParam("biografia") String biografia,
    		Model model)
    {
		this.artistaService.saveArtistaToDB(file, nome, cognome, dataDiNascita, dataDiMorte, luogoDiNascita,luogoDiMorte,biografia);
		model.addAttribute("artisti", this.artistaService.tuttiArtisti());
    	return "inserisci_artista_amm.html";
    }
	

}
