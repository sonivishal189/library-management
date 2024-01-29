package vishal.test.library.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vishal.test.library.entity.Book;
import vishal.test.library.service.BookService;
import vishal.test.library.util.ResponsePayload;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/books")
@Tag(name = "Book Operations", description = "APIs for book operations")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    @Operation(summary = "Add new Book")
    public ResponsePayload<Book> addNewBook(@Valid @RequestBody Book book) {
        log.info("Got request to add new Book: {}", book);
        Book newBook = bookService.addNewBook(book);
        return new ResponsePayload<>(newBook, null);
    }

    @GetMapping
    @Operation(summary = "Get all Books")
    public ResponsePayload<List<Book>> getAllBooks() {
        log.info("Got request to get all Books");
        List<Book> allBooks = bookService.getAllBooks();
        return new ResponsePayload<>(allBooks, null);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by id")
    public ResponsePayload<Book> getBookById(@PathVariable("id") int id) {
        log.info("Got request to get Book with id {}", id);
        Book bookById = bookService.getBookById(id);
        return new ResponsePayload<>(bookById, null);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing book")
    public ResponsePayload<Book> updateBook(@PathVariable("id") int id, @Valid @RequestBody Book book) {
        log.info("Got request to update Book with id {} to {}", id, book);
        Book updatedBook = bookService.updateBook(id, book);
        return new ResponsePayload<>(updatedBook, null);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book by id")
    public ResponsePayload<String> deleteBookById(@PathVariable("id") int id) {
        log.info("Got request to delete Book with id {}", id);
        String deleteResponse = bookService.deleteBookById(id);
        return new ResponsePayload<>(deleteResponse, null);
    }
}
