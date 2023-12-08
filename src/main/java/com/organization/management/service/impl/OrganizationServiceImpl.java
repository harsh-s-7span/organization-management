package com.organization.management.service.impl;

import com.organization.management.entity.Organization;
import com.organization.management.enums.ResultCode;
import com.organization.management.exception.CustomException;
import com.organization.management.repository.OrganizationRepository;
import com.organization.management.service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final MessageSource messageSource;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository,
                                   MessageSource messageSource)
    {
        this.organizationRepository = organizationRepository;
        this.messageSource = messageSource;
    }

    @Override
    public Organization save(Organization organization) {

        log.info("Saving organization {}", organization.getName());
        log.debug("Checking for the organization having same name {}", organization.getName());

        // Check for existed organization
        Optional<Organization> existedOrganization = organizationRepository.findByName(organization.getName());
        if(existedOrganization.isPresent())
            throw new CustomException(ResultCode.INVALID_OPERATION.getValue(),
                    messageSource.getMessage("api.error.already.exists", new String[] {organization.getName()}, Locale.getDefault()));

        Organization savedOrganization = organizationRepository.save(organization);
        log.info("Organization {} saved successfully", savedOrganization.getName());

        return savedOrganization;
    }
}
