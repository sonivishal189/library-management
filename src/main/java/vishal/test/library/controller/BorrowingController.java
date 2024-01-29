package vishal.test.library.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vishal.test.library.entity.BorrowingRecord;
import vishal.test.library.service.BorrowingService;
import vishal.test.library.util.ResponsePayload;

@Slf4j
@RestController
@RequestMapping("/api")
@Tag(name = "Borrow/Return Book", description = "API to borrow or return book")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    @Operation(summary = "Borrow a Book")
    public ResponsePayload<BorrowingRecord> borrowBook(@PathVariable("bookId") int bookId, @PathVariable("patronId") int patronId) {
        log.info("Patron {} wants to borrow book {}", patronId, bookId);
        BorrowingRecord borrowingRecord = borrowingService.borrowBook(bookId, patronId);
        return new ResponsePayload<>(borrowingRecord, null);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    @Operation(summary = "Return a Book")
    public ResponsePayload<BorrowingRecord> returnBook(@PathVariable("bookId") int bookId, @PathVariable("patronId") int patronId) {
        log.info("Patron {} wants to return book {}", patronId, bookId);
        BorrowingRecord borrowingRecord = borrowingService.returnBook(bookId, patronId);
        return new ResponsePayload<>(borrowingRecord, null);
    }
}
