package it.uniroma3.siw.museo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Curatore;

public interface CollezioneRepository extends CrudRepository<Collezione, Long> {

	public Collezione findByNome(String nome);
	
	// metodo non usato nel Service
	public List<Collezione> findByCuratore(Curatore curatore);
	
	public List<Collezione> findAllByOrderByNomeAsc();
}
