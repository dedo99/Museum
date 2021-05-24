package it.uniroma3.siw.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GeneralController {
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String visualizzaHome() {
		return "index.html";
	}
	
	
	@RequestMapping(value = "/informazioni", method = RequestMethod.GET)
	public String visualizzaInfomazioni() {
		return "informazioni.html";
	}
	
	
	

}
