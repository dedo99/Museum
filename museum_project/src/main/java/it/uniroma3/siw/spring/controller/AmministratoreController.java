package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class AmministratoreController {
	
	@Autowired
	private OperaService operaService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String visualizzaHomeAmm() {
		return "home_amm.html";
	}
	
	@RequestMapping(value = "/insertOpera", method = RequestMethod.GET)
	public String visualizzaInserisciOpera(Model model) {
		model.addAttribute("opere", this.operaService.findAllOpera());
		return "inserisci_opera_amm.html";
	}




}