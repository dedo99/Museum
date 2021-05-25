package it.uniroma3.siw.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AmministratoreController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String visualizzaHomeAmm() {
		return "home_amm.html";
	}
	
	@RequestMapping(value = "/insertOpera", method = RequestMethod.GET)
	public String visualizzaInserisciOpera() {
		return "inserisci_opera_amm.html";
	}




}
