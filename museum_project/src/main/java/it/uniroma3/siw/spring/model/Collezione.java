package it.uniroma3.siw.spring.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.*;
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
	private Curatore curatore;
	
	@OneToMany(mappedBy="collezione")
	private List<Opera> listaOpere;
	
	@Lob
	private String image;
}
