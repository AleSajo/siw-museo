package it.uniroma3.siw.museo.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Data
@ToString(includeFieldNames=true)
public class Opera {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@NonNull
	@Column(nullable=false)
	private String titolo;
	
	@Column
	private int anno;
	
	@Column
	private String descrizione;
	
	@Column
	private String immagine;	//link all'immagine dell'opera
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private Artista artista;
	
	@ManyToOne(fetch=FetchType.EAGER,
			cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	private Collezione collezione;
	
	public Opera() {
		
	}
}
