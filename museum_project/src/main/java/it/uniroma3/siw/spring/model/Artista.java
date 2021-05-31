package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
public class Artista {

	@Column(nullable = false)
	@Id
	private String nome;
	
	@NonNull
	private String cognome;
	
	@Column(nullable = false)
	private String dataDiNascita;
	
	@Column(nullable = false)
	private String luogoDiNascita;

	@NonNull
	private String dataDiMorte;
	
	@NonNull
	private String luogoDiMorte;
	
	@NonNull
	private String nazionalita;
	
	@NonNull
	private String biografia;
	
	@OneToMany(mappedBy="artista",cascade= {CascadeType.ALL})
	private List<Opera> listaOpere;
	
	@Lob
	private String image;
}
