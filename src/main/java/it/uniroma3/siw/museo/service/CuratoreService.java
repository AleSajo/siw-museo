package it.uniroma3.siw.museo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.museo.model.Curatore;
import it.uniroma3.siw.museo.repository.CuratoreRepository;

@Service
public class CuratoreService {

	@Autowired
	private CuratoreRepository curatoreRepository;
	
	@Transactional
	public Curatore inserisci(Curatore curatore) {
		return curatoreRepository.save(curatore);
	}
	
	@Transactional
	public void rimuovi(Curatore curatore) {
		curatoreRepository.delete(curatore);
	}
	
	@Transactional
	public List<Curatore> tutti() {
		return (List<Curatore>) curatoreRepository.findAll();
	}
	
	@Transactional
	public List<Curatore> curatorePerNome(String nome) {
		return curatoreRepository.findByNome(nome);
	}
	
	@Transactional
	public List<Curatore> curatorePerCognome(String cognome) {
		return curatoreRepository.findByCognome(cognome);
	}
	
	@Transactional
	public Curatore curatorePerNomeECognome(String nome, String cognome) {
		return curatoreRepository.findByNomeAndCognome(nome, cognome);
	}

	@Transactional
	public boolean exists(Curatore curatore) {
		if(this.curatorePerNomeECognome(curatore.getNome(), curatore.getCognome()) != null) {
			return true;
		} else {
			return false;
		}
	}
}
