package vishal.test.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vishal.test.library.entity.Patron;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Integer> {
}
