package vishal.test.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@Entity
@Table(name = "BOOK")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Book title cannot be null or empty")
    private String title;

    @NotBlank(message = "Book Author cannot be null or empty")
    private String author;

    @Range(min = 1000, max = 2024, message = "Book Publication Year cannot be less than 1000 and more than 2024")
    @NotNull(message = "Book Publication Year cannot be null or empty")
    private int publicationYear;

    private String isbn;

}
