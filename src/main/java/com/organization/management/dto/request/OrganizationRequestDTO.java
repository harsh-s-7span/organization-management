package com.organization.management.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationRequestDTO {

    @NotNull(message = "Organization name cannot be null")
    @NotEmpty(message = "Organization name cannot be empty")
    @NotBlank(message = "Organization name cannot be blank")
    private String name;

    private String description;

    @NotNull(message = "Organization address cannot be null")
    @NotEmpty(message = "Organization address cannot be empty")
    @NotBlank(message = "Organization address cannot be blank")
    private String address;

    @Email(message = "Please provide a valid email address")
    private String email;

    @Size(max = 15, message = "Phone number must be at most 15 characters")
    private String phone;
}
