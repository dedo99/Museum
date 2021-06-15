package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.controller.validator.OperaValidator;
import it.uniroma3.siw.spring.model.Credenziali;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.CredenzialiService;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class OperaController {
	
	@Autowired
	private OperaService operaService;
	
	@Autowired
	private CredenzialiService credenzialiService;
	
	@Autowired
	private OperaValidator operaValidator;
	
	@RequestMapping(value = "/opera/{titolo}", method = RequestMethod.GET)
    public String getCollezione(@PathVariable("titolo") String titolo, Model model) {
    	model.addAttribute("opera", this.operaService.operaPerId(titolo));
    	return "opera.html";
    }
	
	@RequestMapping(value = "/admin/insertOpera", method = RequestMethod.GET)
	public String visualizzaInserisciOpera(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credenziali credentials = credenzialiService.getCredentials(userDetails.getUsername());
    	model.addAttribute("admin",credentials.getAdmin());
		model.addAttribute("opera",new Opera());
		model.addAttribute("opere", this.operaService.findAllOpera());
		return "admin/inserisci_opera_amm.html";
	}
	
	@RequestMapping(value = "/admin/addOpera", method = RequestMethod.POST)
    public String saveOpera(@RequestParam("file") MultipartFile file,
    		@ModelAttribute("opera") Opera opera,
    		Model model,
    		BindingResult bindingResult)
    {
		this.operaValidator.validate(opera, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.operaService.saveOperaToDB(file,opera);
			model.addAttribute("opera",new Opera());
		}
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credenziali credentials = credenzialiService.getCredentials(userDetails.getUsername());
    	model.addAttribute("admin",credentials.getAdmin());
		model.addAttribute("opere", this.operaService.findAllOpera());
    	return "admin/inserisci_opera_amm.html";
    }
	
	@RequestMapping(value = "/admin/deleteOpera", method = RequestMethod.GET)
	public String visualizzaCancellaOpera(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credenziali credentials = credenzialiService.getCredentials(userDetails.getUsername());
    	model.addAttribute("admin",credentials.getAdmin());
		model.addAttribute("opere", this.operaService.findAllOpera());
		return "admin/cancella_opera_amm.html";
	}
	
	@RequestMapping(value = "/admin/deleteOpera", method = RequestMethod.POST)
	public String visualizzaCancellaOpera(Model model, @RequestParam("titolo") String titolo) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credenziali credentials = credenzialiService.getCredentials(userDetails.getUsername());
    	model.addAttribute("admin",credentials.getAdmin());
		Opera o = this.operaService.operaPerId(titolo);
		this.operaService.cancellaOpera(o);
		model.addAttribute("opere", this.operaService.findAllOpera());
		return "admin/cancella_opera_amm.html";
	}
}
