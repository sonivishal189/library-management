package vishal.test.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import vishal.test.library.entity.Book;
import vishal.test.library.entity.BorrowingRecord;
import vishal.test.library.entity.Patron;
import vishal.test.library.exception.BorrowingException;
import vishal.test.library.repo.BorrowingRepository;

import java.util.Date;

@SpringBootTest
public class BorrowingServiceTest {

    @Mock
    private BorrowingRepository borrowingRepository;
    @Mock
    private BookService bookService;
    @Mock
    private PatronService patronService;

    @InjectMocks
    private BorrowingService borrowingService;

    private final Book borrowedBook = new Book(1000, "Test Borrowed Book", "Test Borrowed Author", 2024, "1234567890123");
    private final Book notBorrowedBook = new Book(2000, "Test Returned Book", "Test Returned Author", 2024, "1234567890123");

    private final Patron patron = new Patron(123, "TEST", "1234567890", "test@test.com");

    private final BorrowingRecord borrowedBookRecord = new BorrowingRecord(1, borrowedBook, patron, new Date(), null);
    private final BorrowingRecord notBorrowedBookRecord = new BorrowingRecord(1, notBorrowedBook, patron, new Date(), null);

    @BeforeEach
    public void setup() {
        Mockito.when(bookService.getBookById(borrowedBook.getId())).thenReturn(borrowedBook);
        Mockito.when(bookService.getBookById(notBorrowedBook.getId())).thenReturn(notBorrowedBook);
        Mockito.when(patronService.getPatronById(Mockito.anyInt())).thenReturn(patron);
    }

    @Test
    void borrowNotBorrowedBookTest() {
        Mockito.when(borrowingRepository.findByBookIdAndPatronIdAndReturnDate(notBorrowedBook.getId(), patron.getId(), null)).thenReturn(null);
        Mockito.when(borrowingRepository.save(Mockito.any())).thenReturn(notBorrowedBookRecord);
        BorrowingRecord borrowingRecord = borrowingService.borrowBook(notBorrowedBook.getId(), patron.getId());
        Assertions.assertNotNull(borrowingRecord);
        Assertions.assertEquals(borrowingRecord.getBook().getId(), notBorrowedBook.getId());
    }

    @Test
    void alreadyBorrowedBookTest() {
        Mockito.when(borrowingRepository.findByBookIdAndPatronIdAndReturnDate(borrowedBook.getId(), patron.getId(), null)).thenReturn(borrowedBookRecord);
        Assertions.assertThrows(BorrowingException.class, () -> borrowingService.borrowBook(borrowedBook.getId(), patron.getId()));
    }

    @Test
    void returnBorrowedBookTest() {
        Mockito.when(borrowingRepository.findByBookIdAndPatronIdAndReturnDate(borrowedBook.getId(), patron.getId(), null)).thenReturn(borrowedBookRecord);
        Mockito.when(borrowingRepository.save(Mockito.any())).thenReturn(borrowedBookRecord);
        BorrowingRecord returnBorrowingRecord = borrowingService.returnBook(borrowedBook.getId(), patron.getId());
        Assertions.assertNotNull(returnBorrowingRecord.getReturnDate());
    }

    @Test
    void returnNotBorrowedBookTest() {
        Mockito.when(borrowingRepository.findByBookIdAndPatronIdAndReturnDate(notBorrowedBook.getId(), patron.getId(), null)).thenReturn(null);
        Assertions.assertThrows(BorrowingException.class, () -> borrowingService.returnBook(notBorrowedBook.getId(), patron.getId()));
    }
}
