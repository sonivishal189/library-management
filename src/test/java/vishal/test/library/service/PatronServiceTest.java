package vishal.test.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import vishal.test.library.entity.Patron;
import vishal.test.library.exception.PatronNotFoundException;
import vishal.test.library.repo.PatronRepository;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PatronServiceTest {

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private PatronService patronService;

    private final Patron patron = new Patron(123, "TEST", "1234567890", "test@test.com");

    @BeforeEach
    public void setup() {
        Mockito.when(patronRepository.save(Mockito.any())).thenReturn(patron);
        Mockito.when(patronRepository.findAll()).thenReturn(List.of(patron));
        Mockito.when(patronRepository.findById(123)).thenReturn(Optional.of(patron));
    }

    @Test
    void addNewPatronTest() {
        Patron newPatron = patronService.addNewPatron(patron);
        Assertions.assertNotNull(newPatron);
        Assertions.assertEquals(newPatron.getId(), patron.getId());
    }

    @Test
    void getAllPatronTest() {
        List<Patron> allPatron = patronService.getAllPatrons();
        Assertions.assertNotNull(allPatron);
        Assertions.assertFalse(allPatron.isEmpty());
    }

    @Test
    void getPatronById() {
        Patron patronById = patronService.getPatronById(patron.getId());
        Assertions.assertNotNull(patronById);
        Assertions.assertEquals(patronById.getId(), patron.getId());
    }

    @Test
    void getPatronByWrongIdTest() {
        Assertions.assertThrows(PatronNotFoundException.class, () -> patronService.getPatronById(0));
    }

    @Test
    void updatePatronTest() {
        Patron updatePatron = patron;
        updatePatron.setName("New Test");
        Patron updatedPatron = patronService.updatePatron(patron.getId(), updatePatron);
        Assertions.assertNotNull(updatedPatron);
        Assertions.assertEquals(updatePatron.getName(), updatedPatron.getName());
    }

    @Test
    void deletePatronTest() {
        Assertions.assertNotNull(patronService.deletePatronById(patron.getId()));
    }
}
