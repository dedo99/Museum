package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	private LocalDate dataDiNascita;
	
	@Column(nullable = false)
	private String luogoDiNascita;

	@NonNull
	private LocalDate dataDiMorte;
	
	@NonNull
	private String luogoDiMorte;
	
	@NonNull
	private String nazionalita;
	
	@NonNull
	private String biografia;
	
	@OneToMany(mappedBy="artista")
	private List<Opera> listaOpere;
}
