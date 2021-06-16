package it.uniroma3.siw.museo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.museo.model.Artista;
import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Opera;

public interface OperaRepository extends CrudRepository<Opera, Long> {
	
	//uso una lista perché potrebbero esserci più opere con lo stesso nome
	public List<Opera> findByTitolo(String titolo);
	
	public List<Opera> findByAnno(int anno);
	
	//TODO metodi per casi d'uso
}
