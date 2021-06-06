package it.uniroma3.siw.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
public class Opera {

	@Id
	@Column(nullable=false)
	private String titolo;
	
	@Column(nullable=false)
	private Integer annoDiRealizzazione;
	
	@NonNull
	@Column(length = 3000)
	private String descrizione;
	
	@ManyToOne
	@NonNull
	private Collezione collezione;
	
	@ManyToOne
	@NonNull
	private Artista artista;
	
	@Lob
	private String image;
}
