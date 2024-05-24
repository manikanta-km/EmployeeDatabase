package EmployWise.EmployeeDatabase.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @NotEmpty(message = "Employee name is required")
    private String employeeName;
    @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits long")
    private String phoneNumber;
    @Email(message = "Email should be valid")
    private String email;
    private UUID reportsTo;
    @URL(message = "Please provide a valid URL for the profile image")
    private String profileImage;
}
