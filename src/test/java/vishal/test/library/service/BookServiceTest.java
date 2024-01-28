package vishal.test.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import vishal.test.library.entity.Book;
import vishal.test.library.exception.BookNotFoundException;
import vishal.test.library.repo.BookRepository;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private final Book book = new Book(1000, "Test Book", "Test Author", 2024, "1234567890123");

    @BeforeEach
    public void setup() {
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(book);
        Mockito.when(bookRepository.findAll()).thenReturn(List.of(book));
        Mockito.when(bookRepository.findById(1000)).thenReturn(Optional.of(book));
    }

    @Test
    void addNewBookTest() {
        Book newBook = bookService.addNewBook(book);
        Assertions.assertNotNull(newBook);
        Assertions.assertEquals(newBook.getId(), book.getId());
    }

    @Test
    void getAllBookTest() {
        List<Book> allBooks = bookService.getAllBooks();
        Assertions.assertNotNull(allBooks);
        Assertions.assertFalse(allBooks.isEmpty());
    }

    @Test
    void getBookByIdTest() {
        Book bookById = bookService.getBookById(book.getId());
        Assertions.assertNotNull(bookById);
        Assertions.assertEquals(bookById.getId(), book.getId());
    }

    @Test
    void getBookByWrongIdTest() {
        Assertions.assertThrows(BookNotFoundException.class, () -> bookService.getBookById(2000));
    }

    @Test
    void updateBookTest() {
        Book updateBook = book;
        updateBook.setAuthor("New Author");
        Book updatedBook = bookService.updateBook(book.getId(), updateBook);
        Assertions.assertNotNull(updatedBook);
        Assertions.assertEquals(updateBook.getAuthor(), updatedBook.getAuthor());
    }

    @Test
    void deleteBookTest() {
        Assertions.assertNotNull(bookService.deleteBookById(book.getId()));
    }
}
