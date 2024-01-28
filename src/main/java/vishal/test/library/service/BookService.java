package vishal.test.library.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vishal.test.library.entity.Book;
import vishal.test.library.exception.BookNotFoundException;
import vishal.test.library.repo.BookRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@CacheConfig(cacheNames = "books")
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Cacheable
    public List<Book> getAllBooks() {
        log.debug("Get all books");
        List<Book> allBooks = bookRepository.findAll();
        log.info("All Books fetched are {}", allBooks);
        return allBooks;
    }

    public Book addNewBook(Book book) {
        log.debug("Add new book: {}", book);
        Book newBook = bookRepository.save(book);
        log.info("New book added is {}", newBook);
        return newBook;
    }

    @Cacheable(key = "#id")
    public Book getBookById(int id) {
        log.debug("Get book having id {}", id);
        Book bookById = null;
        Optional<Book> bookFromDb = bookRepository.findById(id);
        if (bookFromDb.isPresent()) {
            bookById = bookFromDb.get();
        } else {
            log.error("No Book found for id " + id);
            throw new BookNotFoundException("No Book found for id " + id);
        }
        log.info("Books fetched for id {} is {}", id, bookById);
        return bookById;
    }

    @CachePut(key = "#id")
    public Book updateBook(int id, Book book) {
        log.debug("Update book id {} with {}", id, book);
        Book bookInDb = getBookById(id);

        bookInDb.setTitle(book.getTitle());
        bookInDb.setAuthor(book.getAuthor());
        bookInDb.setIsbn(book.getIsbn());
        bookInDb.setPublicationYear(book.getPublicationYear());

        Book updatedBook = bookRepository.save(bookInDb);
        log.info("Updated book is {}", updatedBook);
        return updatedBook;
    }

    @CacheEvict(key = "#id")
    public String deleteBookById(int id) {
        log.debug("Delete Book with id {}", id);
        Book bookInDb = getBookById(id);
        bookRepository.delete(bookInDb);
        log.info("Deleted Successfully.");
        return "Book with id " + id + " Deleted Successfully";
    }
}
