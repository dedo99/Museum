package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;

public interface OperaRepository extends CrudRepository<Opera,String> {

	public List<Opera> findByArtista(Artista artista);
	
	public List<Opera> findByAnnoDiRealizzazione(Integer annoDiRealizzazione);
	
	public List<Opera> findByCollezione(Collezione collezione);
	
//	@Query("SELECT o FROM opera ORDER BY RANDOM() LIMIT 3")
//	@Query("SELECT o FROM opera WHERE o.annoDiRealizzazione > :anno")
//	public List<Opera> findTreCasuali(@Param("anno") String anno);
	
}
