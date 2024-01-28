package vishal.test.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "PATRON")
@NoArgsConstructor
@AllArgsConstructor
public class Patron {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Name cannot be null or empty")
    private String name;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Phone Number")
    @NotNull(message = "Phone Number cannot be null or empty")
    private String phoneNumber;

    @Email(message = "Invalid Email")
    private String email;
}
