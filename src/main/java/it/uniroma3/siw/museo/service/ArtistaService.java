package it.uniroma3.siw.museo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.museo.model.Artista;
import it.uniroma3.siw.museo.repository.ArtistaRepository;
import lombok.NonNull;

@Service
public class ArtistaService {
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Transactional
	public Artista inserisci(Artista artista) {
		logger.debug("ArtistaService inserisci");
		return artistaRepository.save(artista);
	}
	
	@Transactional
	public void rimuovi(Artista artista) {
		artistaRepository.delete(artista);
	}
	
	@Transactional
	public List<Artista> tutti() {
		return (List<Artista>) artistaRepository.findAll();
	}
	
	@Transactional
	public Artista artistaPerId(Long id) {
		Optional<Artista> optional = artistaRepository.findById(id);
		return optional.orElse(null);
	}
	
	@Transactional
	public List<Artista> artistiPerNome(String nome) {
		return artistaRepository.findByNome(nome);
	}
	
	@Transactional
	public List<Artista> artistiPerCognome(String cognome) {
		return artistaRepository.findByCognome(cognome);
	}
	
	@Transactional
	public Artista artistaPerNomeECognome(String nome, String cognome) {
		return artistaRepository.findByNomeAndCognome(nome, cognome);
	}
	
	@Transactional
	public List<Artista> artistiPerNazionalita(String nazionalita) {
		return artistaRepository.findByNazionalita(nazionalita);
	}

	@Transactional
	public boolean exists(String nome, String cognome) {
		if(this.artistaPerNomeECognome(nome, cognome) != null) {
			return true;
		} else {
			return false;
		}
	}
}