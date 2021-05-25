package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.repository.ArtistaRepository;

@Service
public class ArtistaService {
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	
	public List<Artista> tuttiArtisti(){
		return (List<Artista>)this.artistaRepository.findAll();
	}
	
	public Artista artistaPerId(String nome) {
		Optional<Artista> opt = this.artistaRepository.findById(nome);
		if(opt.isPresent())
			return opt.get();
		else return null;
	}

}
