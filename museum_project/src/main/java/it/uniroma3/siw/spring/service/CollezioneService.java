package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.repository.CollezioneRepository;

@Service
public class CollezioneService {

	@Autowired 
	private CollezioneRepository collezionerepository;
	
	@Transactional
	public Collezione inserisciCollezione(Collezione c) {
		return this.collezionerepository.save(c);
	}
	
	public List<Collezione> tutteCollezioni() {
		return (List<Collezione>) this.collezionerepository.findAll();
	}
	
	public Collezione collezionePerId(String nome) {
		Optional<Collezione> opt = this.collezionerepository.findById(nome);
		if(opt.isPresent())
			return opt.get();
		else return null;
	}
	
	public boolean esisteCollezione(Collezione c) {
		Collezione collezione = this.collezionePerId(c.getNome());
		return collezione != null;
	}
}
