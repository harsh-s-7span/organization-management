package com.organization.management.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDTO {

    private Long id;

    @NotNull(message = "First name cannot be null")
    @NotEmpty(message = "First name cannot be empty")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @NotEmpty(message = "Last name cannot be empty")
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Email(message = "Please provide a valid email address")
    private String email;

    @Size(max = 15, message = "Phone number must be at most 15 characters")
    @Pattern(regexp = "^[0-9\\s\\-\\(\\)]+$",
            message = "Please provide a valid phone number")
    private String phoneNumber;

    @Positive(message = "Age must be a positive number")
    @Min(value = 18, message = "Age must not be less than 18")
    private Long age;

    private String address;

    private char gender;

    @NotNull(message = "Department ID cannot be null")
    private Long departmentId;

    @NotNull(message = "Role ID cannot be null")
    private Long roleId;

    @NotNull(message = "Sub Organization ID cannot be null")
    private Long subOrganizationId;

    @NotNull(message = "Hire date cannot be null")
    @PastOrPresent(message = "Hire date must be in the past or present")
    private LocalDate hireDate;

    @NotNull(message = "Salary cannot be null")
    private BigDecimal salary;

    private boolean activeStatus;
}
