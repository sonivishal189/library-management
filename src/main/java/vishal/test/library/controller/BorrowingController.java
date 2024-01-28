package vishal.test.library.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vishal.test.library.entity.BorrowingRecord;
import vishal.test.library.service.BorrowingService;
import vishal.test.library.util.ResponsePayload;

@Slf4j
@RestController
@RequestMapping("/api")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponsePayload<BorrowingRecord> borrowBook(@PathVariable("bookId") int bookId, @PathVariable("patronId") int patronId) {
        log.info("Patron {} wants to borrow book {}", patronId, bookId);
        BorrowingRecord borrowingRecord = borrowingService.borrowBook(bookId, patronId);
        return new ResponsePayload<>(borrowingRecord, null);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponsePayload<BorrowingRecord> returnBook(@PathVariable("bookId") int bookId, @PathVariable("patronId") int patronId) {
        log.info("Patron {} wants to return book {}", patronId, bookId);
        BorrowingRecord borrowingRecord = borrowingService.returnBook(bookId, patronId);
        return new ResponsePayload<>(borrowingRecord, null);
    }
}
