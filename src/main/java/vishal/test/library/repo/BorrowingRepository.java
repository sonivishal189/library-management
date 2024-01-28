package vishal.test.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import vishal.test.library.entity.BorrowingRecord;

import java.util.Date;

public interface BorrowingRepository extends JpaRepository<BorrowingRecord, Integer> {
    BorrowingRecord findByBookIdAndPatronIdAndReturnDate(int bookId, int patronId, Date returnDate);
}
