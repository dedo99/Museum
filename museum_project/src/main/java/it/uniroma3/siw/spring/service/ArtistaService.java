package it.uniroma3.siw.spring.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.repository.ArtistaRepository;

@Service
public class ArtistaService {
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	@Transactional
	public Artista inserisciArtista(Artista artista) {
		return this.artistaRepository.save(artista);
	}
	
	@Transactional
	public void cancellaArtista(Artista artista) {
		this.artistaRepository.delete(artista);
	}
	
	@Transactional
	public List<Artista> tuttiArtisti(){
		return (List<Artista>)this.artistaRepository.findAll();
	}
	
	@Transactional
	public Artista artistaPerId(Long id) {
		Optional<Artista> opt = this.artistaRepository.findById(id);
		if(opt.isPresent())
			return opt.get();
		else return null;
	}
	
	@Transactional
	public boolean alreadyExists(Artista artista) {
		Optional<Artista> opt = this.artistaRepository.findById(artista.getId());
		return opt.isPresent();
	}
	
	@Transactional
	public boolean alreadyExistsByNomeAndCognome(Artista artista) {
		List<Artista> artisti = this.artistaRepository.findByNomeAndCognome(artista.getNome(), artista.getCognome());
		return artisti.size() > 0;
	}
	
	@Transactional
	public void  saveArtistaToDB(MultipartFile file, Artista artista){
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try {
			artista.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(artista.getDataDiMorte().equals(""))
			artista.setDataDiMorte("-/-/-");
		if(artista.getLuogoDiMorte().equals(""))
			artista.setLuogoDiMorte("---");
        this.inserisciArtista(artista);
	}
}
