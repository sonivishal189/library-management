package vishal.test.library.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vishal.test.library.entity.Patron;
import vishal.test.library.exception.PatronNotFoundException;
import vishal.test.library.repo.PatronRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@CacheConfig(cacheNames = "patrons")
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    public Patron addNewPatron(Patron patron) {
        log.debug("Create new patron {}", patron);
        Patron newPatron = patronRepository.save(patron);
        log.info("New Patron added is {}", newPatron);
        return newPatron;
    }

    @Cacheable
    public List<Patron> getAllPatrons() {
        log.debug("Get all patrons");
        List<Patron> allPatrons = patronRepository.findAll();
        log.info("All Patron fetched is {}", allPatrons);
        return allPatrons;
    }

    @Cacheable(key = "#id")
    public Patron getPatronById(int id) {
        log.debug("Get patron for id {}", id);
        Patron patronById = null;
        Optional<Patron> patronInDb = patronRepository.findById(id);
        if (patronInDb.isPresent()) {
            patronById = patronInDb.get();
        } else {
            throw new PatronNotFoundException("No Patron found for id " + id);
        }
        log.info("Patron fetched is {}", patronById);
        return patronById;
    }

    @CachePut(key = "#id")
    public Patron updatePatron(int id, Patron patron) {
        log.debug("Update Patron id {} with {}", id, patron);
        Patron patronInDb = getPatronById(id);

        patronInDb.setEmail(patron.getEmail());
        patronInDb.setName(patron.getName());
        patronInDb.setPhoneNumber(patron.getPhoneNumber());

        Patron updatedPatron = patronRepository.save(patronInDb);
        log.info("Update patron is {}", updatedPatron);
        return updatedPatron;
    }

    @CacheEvict(key = "#id")
    public String deletePatronById(int id) {
        log.debug("Delete Patron with id {}", id);
        Patron patronInDb = getPatronById(id);
        patronRepository.delete(patronInDb);
        log.info("Deleted Successfully.");
        return "Patron with id " + id + " Deleted Successfully.";
    }
}
