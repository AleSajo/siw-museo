package it.uniroma3.siw.museo.model;

import java.util.*;
import javax.persistence.*;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Data
@ToString(includeFieldNames=true)
public class Collezione {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NonNull
	@Column(nullable=false)
	private String nome;
	
	@Column
	private String descrizione;
	
	@ManyToOne(cascade={CascadeType.ALL})
	private Curatore curatore;
	
	@OneToMany(mappedBy="collezione",
			fetch=FetchType.EAGER,
			cascade={CascadeType.ALL})
	private List<Opera> opere;
	
	public Collezione() {
		this.curatore = new Curatore();
		this.opere = new ArrayList<Opera>();
	}
}
