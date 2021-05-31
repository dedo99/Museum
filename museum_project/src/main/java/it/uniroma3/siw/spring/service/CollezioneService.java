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
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Curatore;
import it.uniroma3.siw.spring.repository.CollezioneRepository;

@Service
public class CollezioneService {

	@Autowired 
	private CollezioneRepository collezionerepository;
	
	@Transactional
	public Collezione inserisciCollezione(Collezione c) {
		return this.collezionerepository.save(c);
	}
	
	@Transactional
	public void deleteCollezione(Collezione c) {
		this.collezionerepository.delete(c);
	}
	
	@Transactional
	public List<Collezione> tutteCollezioni() {
		return (List<Collezione>) this.collezionerepository.findAll();
	}
	
	@Transactional
	public Collezione collezionePerId(String nome) {
		Optional<Collezione> opt = this.collezionerepository.findById(nome);
		if(opt.isPresent())
			return opt.get();
		else return null;
	}
	
	@Transactional
	public void  saveCollezioneToDB(MultipartFile file,String nome,
			String descrizione, Curatore curatore){
		Collezione c = new Collezione();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try {
			c.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		c.setNome(nome);
		c.setDescrizione(descrizione);
		c.setCuratore(curatore);
        this.inserisciCollezione(c);
	}
}
