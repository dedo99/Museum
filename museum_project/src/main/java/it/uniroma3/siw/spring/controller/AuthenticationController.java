package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.Amministratore;
import it.uniroma3.siw.spring.model.Credenziali;
import it.uniroma3.siw.spring.service.CredenzialiService;

public class AuthenticationController {
	
	@Autowired
	private CredenzialiService credenzialiService;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String visualizzaFormLogin() {
		return "login_amm.html";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutAdmin() {
		return "index.html";
	}
	
    @RequestMapping(value = "/admin/home_amm", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {
        
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credenziali credentials = credenzialiService.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credenziali.ADMIN_ROLE)) {
            return "home_amm.html";
        }
    	return "index.html";
    }
    
    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public void registerUser() {

        Credenziali c = Credenziali.builder().username("pippo").password("pluto").role(Credenziali.ADMIN_ROLE).build();
        Amministratore a = Amministratore.builder().nome("Mario").cognome("Rossi").build();
        c.setAdmin(a);
        credenzialiService.saveCredentials(c);
    }
	

}
