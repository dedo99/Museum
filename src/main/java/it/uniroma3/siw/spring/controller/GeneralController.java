package it.uniroma3.siw.spring.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.OperaService;

@Controller
public class GeneralController {
	
	@Autowired
	private OperaService operaService;
	
	@RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
	public String index(Model model) {
		List<Opera> opere = this.operaService.findAllOpera();
		Collections.shuffle(opere);
		List<Opera> nuova = opere.subList(0, 4);
		model.addAttribute("opera1", nuova.get(0));
		model.addAttribute("opera2", nuova.get(1));
		model.addAttribute("opera3", nuova.get(2));
		model.addAttribute("opera4", nuova.get(3));
		return "index";
	}
	
	@RequestMapping(value = "/informazioni", method = RequestMethod.GET)
	public String visualizzaInfomazioni() {
		return "informazioni";
	}
}
