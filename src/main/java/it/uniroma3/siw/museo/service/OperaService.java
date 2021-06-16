package it.uniroma3.siw.museo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.museo.model.Opera;
import it.uniroma3.siw.museo.repository.OperaRepository;

@Service
public class OperaService {
	
	@Autowired
	private OperaRepository operaRepository;
	
	@Transactional
	public Opera inserisci(Opera opera) {
		return operaRepository.save(opera);
	}
	
	@Transactional
	public void rimuovi(Opera opera) {
		operaRepository.delete(opera);
	}
	
	@Transactional
	public List<Opera> tutte() {
		return (List<Opera>) operaRepository.findAll();
	}
	
	@Transactional
	public Opera operaPerId(Long id) {
		if(operaRepository.findById(id).isPresent())
			return operaRepository.findById(id).get();
		else
			return null;
	}
	
	@Transactional
	public List<Opera> operePerTitolo(String titolo) {
		return (List<Opera>)operaRepository.findByTitolo(titolo);
	}
	
	@Transactional
	public List<Opera> operePerAnno(int anno) {
		return operaRepository.findByAnno(anno);
	}
}
