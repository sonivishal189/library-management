package vishal.test.library.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vishal.test.library.entity.Patron;
import vishal.test.library.service.PatronService;
import vishal.test.library.util.ResponsePayload;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/patrons")
@Tag(name = "Patron Operation", description = "APIs for patron operations")
public class PatronController {

    @Autowired
    private PatronService patronService;

    @PostMapping
    @Operation(summary = "Add new Patron")
    public ResponsePayload<Patron> addNewPatron(@Valid @RequestBody Patron patron) {
        log.info("Got request to add new Patron {}", patron);
        Patron newPatron = patronService.addNewPatron(patron);
        return new ResponsePayload<>(newPatron, null);
    }

    @GetMapping
    @Operation(summary = "Get all Patrons")
    public ResponsePayload<List<Patron>> getAllPatron() {
        log.info("Got request to get all Patron");
        List<Patron> allPatrons = patronService.getAllPatrons();
        return new ResponsePayload<>(allPatrons, null);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Patron by id")
    public ResponsePayload<Patron> getBookById(@PathVariable("id") int id) {
        log.info("Got request to get Patron with id {}", id);
        Patron patronById = patronService.getPatronById(id);
        return new ResponsePayload<>(patronById, null);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Patron")
    public ResponsePayload<Patron> updateBook(@PathVariable("id") int id, @Valid @RequestBody Patron patron) {
        log.info("Got request to update Patron with id {} to {}", id, patron);
        Patron updatedPatron = patronService.updatePatron(id, patron);
        return new ResponsePayload<>(updatedPatron, null);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete Patron by id")
    public ResponsePayload<String> deleteById(@PathVariable("id") int id) {
        log.info("Got request to delete Patron with id {}", id);
        String deleteResponse = patronService.deletePatronById(id);
        return new ResponsePayload<>(deleteResponse, null);
    }
}
