package org.nira.usermanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @NotEmpty(message = "user first name shouldn't be null or empty")
    private String firstName;
    @NotEmpty(message = "User last name shouldn't be null or empty")
    private String lastName;
    @NotEmpty(message = "User email shouldn't be null or empty")
    @Email(message = "Invalid email format")
    private String email;
}
