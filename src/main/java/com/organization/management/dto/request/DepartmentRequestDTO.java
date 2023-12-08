package com.organization.management.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequestDTO {

    private Long id;

    @NotNull(message = "Department name cannot be null")
    @NotEmpty(message = "Department name cannot be empty")
    @NotBlank(message = "Department name cannot be blank")
    private String name;

    private String description;
}
