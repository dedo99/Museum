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
	
	@RequestMapping(value = "/admin/homeAmm", method = RequestMethod.GET)
	public String visualizzaHomeAmm() {
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
            return "admin/home_amm";
        }
    	return "index";
    }
    
    @RequestMapping(value = { "/register" }, method = RequestMethod.GET)
    public String registerUser() {

        Credenziali c = Credenziali.builder().username("pippo").password("pluto").role(Credenziali.ADMIN_ROLE).build();
        Amministratore a = Amministratore.builder().nome("Mario").cognome("Rossi").build();
        c.setAdmin(a);
        credenzialiService.saveCredentials(c);
        return "login_amm";
    }
    
//    @RequestMapping(value = "/default", method = RequestMethod.GET)
//    public String defaultAfterLogin(Model model,
//    		@ModelAttribute("credenziali") Credenziali credenziali,
//    		BindingResult bindingResult) {
//        
//    	this.credenzialiValidator.validate(credenziali, bindingResult);
//    	if(!bindingResult.hasErrors()) {
//    		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        	Credenziali credentials = credenzialiService.getCredentials(userDetails.getUsername());
//        	if (credentials.getRole().equals(Credenziali.ADMIN_ROLE)) {
//                return "admin/home_amm";
//            }
//        	return "index";
//    	}
//    	return "login_amm";
//    }
	

}
