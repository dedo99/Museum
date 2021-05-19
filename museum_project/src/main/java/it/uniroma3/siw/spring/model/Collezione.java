package it.uniroma3.siw.spring.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class Collezione {

	@Id
	@Column(nullable=false)
	private String nome;

	@NonNull
	private String descrizione;
	
	@ManyToOne
	private Curatore curatore;
	
	@OneToMany(mappedBy="collezione")
	private List<Opera> listaOpere;
	
	public Collezione(String nome, String descrizione) {
		this.nome = nome;
		this.descrizione = descrizione;
	}
	public Collezione(List<Opera> listaOpere) {
		this.listaOpere = listaOpere;
	}
}
