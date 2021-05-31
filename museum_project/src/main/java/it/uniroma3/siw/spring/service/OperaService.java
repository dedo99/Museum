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
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.repository.OperaRepository;

@Service
public class OperaService {

	@Autowired
	private OperaRepository operarepository;
	
	@Transactional
	public Opera inserisciOpera(Opera opera) {
		return this.operarepository.save(opera);
	}
	
	@Transactional
	public void cancellaOpera(Opera opera) {
		this.operarepository.delete(opera);
	}
	
	@Transactional
	public List<Opera> findOpereByArtista(Artista artista) {
		return this.operarepository.findByArtista(artista);
	}
	
	@Transactional
	public List<Opera> findOpereByCollezione(Collezione collezione) {
		return this.operarepository.findByCollezione(collezione);
	}
	
	@Transactional
	public List<Opera> findAllOpera(){
		return (List<Opera>) this.operarepository.findAll();
	}
	
	@Transactional
	public Opera operaPerId(String titolo) {
		Optional<Opera> opt = this.operarepository.findById(titolo);
		if(opt.isPresent())
			return opt.get();
		else return null;
	}
	
	@Transactional
	public void  saveOperaToDB(MultipartFile file,String titolo,
			String descrizione, Integer anno, Collezione col, Artista art){
		Opera o = new Opera();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try {
			o.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		o.setTitolo(titolo);
		o.setAnnoDiRealizzazione(anno);
		o.setDescrizione(descrizione);
		o.setArtista(art);
		o.setCollezione(col);
        
        this.inserisciOpera(o);
	}
}
