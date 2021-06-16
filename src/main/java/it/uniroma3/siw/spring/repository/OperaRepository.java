package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;

public interface OperaRepository extends CrudRepository<Opera,String> {

	public List<Opera> findByArtista(Artista artista);
	
	public List<Opera> findByAnnoDiRealizzazione(Integer annoDiRealizzazione);
	
	public List<Opera> findByCollezione(Collezione collezione);
}
