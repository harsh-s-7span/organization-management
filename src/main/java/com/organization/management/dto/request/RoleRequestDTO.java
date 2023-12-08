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
public class RoleRequestDTO {

    private Long id;

    @NotNull(message = "Role name cannot be null")
    @NotEmpty(message = "Role name cannot be empty")
    @NotBlank(message = "Role name cannot be blank")
    private String name;

    private String description;
}
