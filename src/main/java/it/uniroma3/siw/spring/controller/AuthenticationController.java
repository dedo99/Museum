package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.Amministratore;
import it.uniroma3.siw.spring.model.Credenziali;
import it.uniroma3.siw.spring.service.CredenzialiService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private CredenzialiService credenzialiService;
	
	
	public void sessionUser(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credenziali credentials = credenzialiService.getCredentials(userDetails.getUsername());
    	model.addAttribute("admin",credentials.getAdmin());
	}
	
	
	@RequestMapping(value = "/admin/homeAmm", method = RequestMethod.GET)
	public String visualizzaHomeAmm(Model model) {
		this.sessionUser(model);
		return "admin/home_amm";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String visualizzaFormLogin() {
		return "login_amm";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) {
		return "index";
	}
	
    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credenziali credentials = credenzialiService.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credenziali.ADMIN_ROLE)) {
    		model.addAttribute("admin",credentials.getAdmin());
            return "admin/home_amm";
        }
    	return "index";
    }
    
    @RequestMapping(value = { "/register" }, method = RequestMethod.GET)
    public String registerUser() {
    	Credenziali c1 = Credenziali.builder().username("paperino").password("rione").role(Credenziali.ADMIN_ROLE).build();
        Amministratore a1 = Amministratore.builder().nome("Giovanni").cognome("Verdi").build();
        c1.setAdmin(a1);
        credenzialiService.saveCredentials(c1);
        Credenziali c2 = Credenziali.builder().username("pippo").password("pluto").role(Credenziali.ADMIN_ROLE).build();
        Amministratore a2 = Amministratore.builder().nome("Mario").cognome("Rossi").build();
        c2.setAdmin(a2);
        credenzialiService.saveCredentials(c2);
        return "login_amm";
    }
}
