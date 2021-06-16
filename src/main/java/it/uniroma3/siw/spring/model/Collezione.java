package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
public class Collezione {

	@Id
	@Column(nullable=false)
	private String nome;

	@NonNull
	private String descrizione;
	
	@ManyToOne
	@NonNull
	private Curatore curatore;
	
	@OneToMany(mappedBy="collezione",cascade= {CascadeType.REMOVE})
	private List<Opera> listaOpere;
	
	@Lob
	private String image;
}
