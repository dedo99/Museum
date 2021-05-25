package it.uniroma3.siw.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.repository.OperaRepository;

@Service
public class OperaService {

	@Autowired
	private OperaRepository operarepository;
	
	public Opera inserisciOpera(Opera opera) {
		return this.operarepository.save(opera);
	}
	
	public Opera operaPerId(String titolo) {
		Optional<Opera> opt = this.operarepository.findById(titolo);
		if(opt.isPresent())
			return opt.get();
		else return null;
	}
}
