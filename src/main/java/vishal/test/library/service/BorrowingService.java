package vishal.test.library.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vishal.test.library.entity.Book;
import vishal.test.library.entity.BorrowingRecord;
import vishal.test.library.entity.Patron;
import vishal.test.library.exception.BorrowingException;
import vishal.test.library.repo.BorrowingRepository;

import java.util.Date;

@Slf4j
@Service
public class BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private PatronService patronService;

    public BorrowingRecord borrowBook(int bookId, int patronId) {
        log.debug("Book {} is getting borrowed by patron {}", bookId, patronId);
        BorrowingRecord newBorrowingRecord = null;

        Book bookInDb = bookService.getBookById(bookId);
        Patron patronInDb = patronService.getPatronById(patronId);

        BorrowingRecord borrowingRecordInDb = borrowingRepository.findByBookIdAndPatronIdAndReturnDate(bookId, patronId, null);
        log.info("Borrowing book record is {}", borrowingRecordInDb);
        if (null == borrowingRecordInDb || null != borrowingRecordInDb.getReturnDate()) {
            BorrowingRecord borrowingRecord = new BorrowingRecord();
            borrowingRecord.setBook(bookInDb);
            borrowingRecord.setPatron(patronInDb);
            borrowingRecord.setBorrowDate(new Date());
            newBorrowingRecord = borrowingRepository.save(borrowingRecord);
        } else {
            log.warn("Patron {} has already borrowed book {} on date {}", patronId, bookId, borrowingRecordInDb.getBorrowDate());
            throw new BorrowingException("Patron " + patronId + " has already borrowed book " + bookId + " on date " + borrowingRecordInDb.getBorrowDate());
        }
        return newBorrowingRecord;
    }

    public BorrowingRecord returnBook(int bookId, int patronId) {
        log.debug("Book {} is getting borrowed by patron {}", bookId, patronId);
        BorrowingRecord borrowingRecordInDb = borrowingRepository.findByBookIdAndPatronIdAndReturnDate(bookId, patronId, null);
        if (borrowingRecordInDb == null) {
            log.error("Patron {} never borrowed book {}", patronId, bookId);
            throw new BorrowingException("Patron " + patronId + " has not borrowed book " + bookId);
        }
        borrowingRecordInDb.setReturnDate(new Date());
        BorrowingRecord returnedBookRecord = borrowingRepository.save(borrowingRecordInDb);
        log.info("Returned record is {}", returnedBookRecord);
        return returnedBookRecord;
    }
}
