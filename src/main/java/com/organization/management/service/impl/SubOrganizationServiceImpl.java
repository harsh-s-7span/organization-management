package com.organization.management.service.impl;

import com.organization.management.entity.SubOrganization;
import com.organization.management.enums.ResultCode;
import com.organization.management.exception.CustomException;
import com.organization.management.repository.SubOrganizationRepository;
import com.organization.management.service.SubOrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
@Slf4j
public class SubOrganizationServiceImpl implements SubOrganizationService {

    private final SubOrganizationRepository subOrganizationRepository;
    private final MessageSource messageSource;

    public SubOrganizationServiceImpl(SubOrganizationRepository subOrganizationRepository,
                                      MessageSource messageSource)
    {
        this.subOrganizationRepository = subOrganizationRepository;
        this.messageSource = messageSource;
    }

    @Override
    public SubOrganization save(SubOrganization subOrganization) {

        log.info("Saving sub organization {}", subOrganization.getName());
        log.debug("Checking for the sub-organization having same name {}", subOrganization.getName());

        // Check for existed sub-organization
        Optional<SubOrganization> existedSubOrganization = subOrganizationRepository.findByName(subOrganization.getName());
        if(existedSubOrganization.isPresent())
            throw new CustomException(ResultCode.INVALID_OPERATION.getValue(), messageSource.getMessage("api.error.already.exists", new String[] {subOrganization.getName()}, Locale.getDefault()));

        SubOrganization savedSubOrganization = subOrganizationRepository.save(subOrganization);
        log.info("Sub organization {} saved successfully", savedSubOrganization.getName());
        return savedSubOrganization;
    }
}
