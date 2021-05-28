package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Amministratore;
import it.uniroma3.siw.spring.repository.AmministratoreRepository;

@Service
public class AmministratoreService {
	
	 @Autowired
	 protected AmministratoreRepository amministratoreRepository;

    /**
     * This method retrieves a User from the DB based on its ID.
     * @param id the id of the User to retrieve from the DB
     * @return the retrieved User, or null if no User with the passed ID could be found in the DB
     */
    @Transactional
    public Amministratore getUser(Long id) {
        Optional<Amministratore> result = this.amministratoreRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * This method saves a User in the DB.
     * @param user the User to save into the DB
     * @return the saved User
     * @throws DataIntegrityViolationException if a User with the same username
     *                              as the passed User already exists in the DB
     */
    @Transactional
    public Amministratore saveUser(Amministratore user) {
        return this.amministratoreRepository.save(user);
    }

    /**
     * This method retrieves all Users from the DB.
     * @return a List with all the retrieved Users
     */
    @Transactional
    public List<Amministratore> getAllAdmin() {
        List<Amministratore> result = new ArrayList<>();
        Iterable<Amministratore> iterable = this.amministratoreRepository.findAll();
        for(Amministratore admin : iterable)
            result.add(admin);
        return result;
    }

}
