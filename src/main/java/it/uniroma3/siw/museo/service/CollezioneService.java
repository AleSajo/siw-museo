package it.uniroma3.siw.museo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.repository.CollezioneRepository;

@Service
public class CollezioneService {
	
	@Autowired
	private CollezioneRepository collezioneRepository;
	
	@Transactional
	public Collezione inserisci(Collezione collezione) {
		return collezioneRepository.save(collezione);
	}
	
	@Transactional
	public void rimuovi(Collezione collezione) {
		collezioneRepository.delete(collezione);
	}
	
	@Transactional
	public List<Collezione> tutte() {
		return (List<Collezione>) collezioneRepository.findAll();
	}
	
	@Transactional
	public List<Collezione> tutteOrdineAlfabetico() {
		return (List<Collezione>) collezioneRepository.findAllByOrderByNomeAsc();
	}
	
	@Transactional
	public Collezione collezionePerId(Long id) {
		Optional<Collezione> optional = collezioneRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			return null;
	}
	
	@Transactional
	public Collezione collezionePerNome(String nome) {
		return collezioneRepository.findByNome(nome);
	}
	
	@Transactional
	public boolean exists(Collezione collezione) {
		if(collezione.getNome().equals(this.collezioneRepository.findByNome(collezione.getNome()))) {
			return true;
		}
		else {
			return false;
		}
	}
}
