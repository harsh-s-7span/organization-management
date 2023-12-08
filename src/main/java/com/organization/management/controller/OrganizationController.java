package com.organization.management.controller;

import com.organization.management.dto.BaseResponse;
import com.organization.management.dto.request.OrganizationRequestDTO;
import com.organization.management.dto.request.SubOrganizationRequestDTO;
import com.organization.management.dto.response.OrganizationResponseDTO;
import com.organization.management.dto.response.SubOrganizationResponseDTO;
import com.organization.management.handler.OrganizationHandler;
import com.organization.management.handler.SubOrganizationHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    private final OrganizationHandler organizationHandler;
    private final SubOrganizationHandler subOrganizationHandler;

    public OrganizationController(OrganizationHandler organizationHandler,
                                  SubOrganizationHandler subOrganizationHandler)
    {
        this.organizationHandler = organizationHandler;
        this.subOrganizationHandler = subOrganizationHandler;
    }

    @PostMapping
    public BaseResponse<OrganizationResponseDTO> save(@Valid @RequestBody OrganizationRequestDTO request)
    {
        return organizationHandler.save(request);
    }

    @PostMapping("/sub")
    public BaseResponse<SubOrganizationResponseDTO> save(@Valid @RequestBody SubOrganizationRequestDTO request)
    {
        return subOrganizationHandler.save(request);
    }
}
