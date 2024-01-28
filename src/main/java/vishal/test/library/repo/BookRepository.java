package vishal.test.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vishal.test.library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
