package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.*;
import lombok.ToString.Exclude;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
public class Curatore {
	
	@Id
	@Column(nullable = false)
	private String matricola;

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
	
	@ToString.Exclude
	@OneToMany(mappedBy="curatore")
	private List<Collezione> listaCollezioni;
}
