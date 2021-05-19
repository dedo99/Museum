package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.model.Persona;
import it.uniroma3.siw.spring.repository.PersonaRepository;

@Service
public class PersonaService {

	@Autowired
	private PersonaRepository personaRepository;
	
	@Transactional
	public Persona inserisci(Persona persona) {
		return personaRepository.save(persona);
	}
	
	public Optional<Persona> findById(Long id) {
		return personaRepository.findById(id);
	}
	
	public List<Persona> findAll() {
		return (List<Persona>) personaRepository.findAll();
	}
	
	public List<Persona> searchNomeOrCognome(String nome,String cognome) {
		return personaRepository.findByNomeOrCognome(nome,cognome);
	}
	
	public Persona save(Persona persona) {
		return personaRepository.save(persona);
	}
	
	public boolean alreadyExists(Persona p) {
		return personaRepository.existsById(p.getId());
	}
}
