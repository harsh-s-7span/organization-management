package com.organization.management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubOrganizationResponseDTO {

    private Long id;

    private String name;

    private String description;

    private String address;

    private String email;

    private String phone;
}
