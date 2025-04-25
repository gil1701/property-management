package com.oproser.property_management.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private Long id;
    private String ownerName;
    @NotNull(message = "Owner email is mandatory")
    @NotEmpty(message = "Owmer email must not be empty")
    @Size(min = 1, max = 50, message = "Owner email must 1 to 50 characters long")
    private String ownerEmail;
    @NotNull(message = "Password must not be null")
    @NotEmpty(message = "Password must not be empty")
    @Size(min = 1, max = 15, message = "Password must be 1 to 15 characters long")
    private String password;
    private String phone;
}
