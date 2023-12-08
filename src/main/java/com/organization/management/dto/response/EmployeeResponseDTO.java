package com.organization.management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Long age;

    private String address;

    private char gender;

    private String departmentName;

    private String roleName;

    private String subOrganizationName;

    private LocalDate hireDate;

    private BigDecimal salary;

    private boolean activeStatus;
}
