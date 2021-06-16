package it.uniroma3.siw.museo.model;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Data
@ToString(includeFieldNames=true)
public class Artista {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NonNull
	@Column(nullable=false)
	private String nome;
	
	@NonNull
	@Column(nullable=false)
	private String cognome;
	
	@Column
	private String nazionalita;
	
	@Column
	private LocalDate dataDiNascita;
	
	@Column
	private String luogoDiNascita;
	
	@Column
	private LocalDate dataDiMorte;
	
	@Column
	private String luogoDiMorte;
	
	@Column
	private String foto;	//link ad un immagine su internet
	
	@OneToMany(mappedBy="artista",
			fetch=FetchType.EAGER,
			cascade=CascadeType.ALL)
	private List<Opera> opere;
	
	public Artista() {
		this.opere=new ArrayList<Opera>();
	}
}