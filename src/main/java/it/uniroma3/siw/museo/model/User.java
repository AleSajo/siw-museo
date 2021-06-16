package it.uniroma3.siw.museo.model;

import javax.persistence.*;

import org.hibernate.annotations.Subselect;

import lombok.Data;

@Entity
@Table(name = "users") // cambiamo nome perchè in postgres user e' una parola riservata
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String cognome;
	
}
