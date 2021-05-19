package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class Curatore {

	@Column(nullable = false)
	private String nome;
	
	@NonNull
	private String cognome;
	
	@Column(nullable = false)
	private String luogoNascita;
	
	@NonNull
	private LocalDate dataNascita;
	
	@Column(nullable = false)
	private String email;
	
	@NonNull
	private String telefono;
	
	@Id
	@Column(nullable = false)
	private String matricola;
	
	@OneToMany(mappedBy="curatore")
	private List<Collezione> listaCollezioni;
	
	public Curatore(String nome, String cognome, String luogoNascita, LocalDate dataNascita, String email,
			        String telefono, String matricola) {
		this.nome = nome;
		this.cognome = cognome;
		this.luogoNascita = luogoNascita;
		this.dataNascita = dataNascita;
		this.email = email;
		this.telefono = telefono;
		this.matricola = matricola;
	}

	public Curatore(List<Collezione> listaCollezioni) {
		this.listaCollezioni = listaCollezioni;
	}
}
