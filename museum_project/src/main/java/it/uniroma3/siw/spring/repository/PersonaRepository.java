package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Persona;

//<classe dell'entità,classe dell'id>
public interface PersonaRepository extends CrudRepository<Persona,Long> {
	
	// con l'estensione, ereditiamo molti dei metodi principali per effettuare le query, vedi sulle slide

	/* possiamo scrivere query a partire dal nome del metodo,senza scrivere codice jpql */
	// operazione che si vuole eseguire(ex. find o count)
	// combinazione di proprietà(attributi) che deve essere verificata nei valori da restituire(ex. nome=? AND cognome=?)
	// i parametri del metodo costituiscono i parametri della query
	
	/* select p
	 * from p
	 * where nome := nome
	 */
	public List<Persona> findByNome(String nome);
	
	public List<Persona> findByNomeOrCognome(String nome,String cognome);
}
