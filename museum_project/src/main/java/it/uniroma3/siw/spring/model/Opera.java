package it.uniroma3.siw.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class Opera {

	@Id
	@Column(nullable=false)
	private String titolo;
	
	@Column(nullable=false)
	private Integer annoDiRealizzazione;
	
	@NonNull
	private String descrizione;
	
	@ManyToOne
	private Collezione collezione;
	
	@ManyToOne
	private Artista artista;

	public Opera(String titolo, Integer annoDiRealizzazione, String descrizione) {
		this.titolo = titolo;
		this.annoDiRealizzazione = annoDiRealizzazione;
		this.descrizione = descrizione;
	}
}
