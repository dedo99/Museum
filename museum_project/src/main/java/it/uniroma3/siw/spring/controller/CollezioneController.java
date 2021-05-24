package it.uniroma3.siw.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class CollezioneController {
	
	@RequestMapping(value = "/collezioni", method = RequestMethod.GET)
	public String getCollezioni() {
		return "collezioni.html";
	}

}
