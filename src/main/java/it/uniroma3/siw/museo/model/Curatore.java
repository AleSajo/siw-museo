package it.uniroma3.siw.museo.model;

import java.time.*;
import java.util.*;
import javax.persistence.*;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Data
@ToString(includeFieldNames=true)
public class Curatore {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NonNull
	@Column(nullable=false)
	private String nome;
	
	@NonNull
	@Column(nullable=false)
	private String cognome;
	
	@Column
	private LocalDate dataDiNascita;
	
	@Column
	private String luogoDiNascita;
	
	@Column
	private String email;
	
	@Column
	private String telefono;
	
	@Column
	private int codice;
	
	@OneToMany(mappedBy="curatore",
			fetch=FetchType.LAZY)
	private List<Collezione> collezioni;

	public Curatore() {
		this.collezioni=new ArrayList<Collezione>();
	}

	//costruttore più completo
	public Curatore(String firstName, String lastName, LocalDate dateOfBirth,
			String placeOfBirth, String email, String phone, int code) {
		this.nome = firstName;
		this.cognome = lastName;
		this.dataDiNascita = dateOfBirth;
		this.luogoDiNascita = placeOfBirth;
		this.email = email;
		this.telefono = phone;
		this.codice = code;
	}

}
