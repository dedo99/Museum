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

@Entity
@Data
@NoArgsConstructor
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

	private LocalDate dataDiMorte;
	
	private String luogoDiMorte;
	
	@NonNull
	private String nazionalita;
	
	@OneToMany(mappedBy="artista")
	private List<Opera> listaOpere;
	
	
	public Artista(String nome, String cognome, LocalDate dataDiNascita, String luogoDiNascita, String nazionalita) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.luogoDiNascita = luogoDiNascita;
		this.nazionalita = nazionalita;
	}
	
	public Artista(List<Opera> listaOpere) {
		this.listaOpere = listaOpere;
	}
}
