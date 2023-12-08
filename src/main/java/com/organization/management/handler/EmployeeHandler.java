package com.organization.management.handler;

import com.organization.management.constant.Constant;
import com.organization.management.dto.BaseResponse;
import com.organization.management.dto.Metadata;
import com.organization.management.dto.request.EmployeeRequestDTO;
import com.organization.management.dto.response.EmployeeResponseDTO;
import com.organization.management.dto.response.EmployeeResponseDTOList;
import com.organization.management.entity.Employee;
import com.organization.management.enums.ResultCode;
import com.organization.management.mapper.EmployeeMapper;
import com.organization.management.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class EmployeeHandler {

    private final EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;
    private final MessageSource messageSource;

    public EmployeeHandler(EmployeeMapper employeeMapper,
                           EmployeeService employeeService,
                           MessageSource messageSource)
    {
        this.employeeMapper = employeeMapper;
        this.employeeService = employeeService;
        this.messageSource = messageSource;
    }

    public BaseResponse<EmployeeResponseDTO> save(EmployeeRequestDTO request)
    {
        Employee savedEmployee = employeeService.save(employeeMapper.requestToEntity(request));

        EmployeeResponseDTO response = employeeMapper.entityToResponse(savedEmployee);

        return new BaseResponse<>(ResultCode.SUCCESS.getValue(),
                messageSource.getMessage("api.successfully.saved.response", new String[] {}, Locale.getDefault()), response);
    }

    public BaseResponse<EmployeeResponseDTO> view(long id)
    {
        Employee dbEmployee = employeeService.findById(id);
        EmployeeResponseDTO response = employeeMapper.entityToResponse(dbEmployee);
        return new BaseResponse<>(ResultCode.SUCCESS.getValue(),
                messageSource.getMessage("api.successfully.fetched.response", new String[] {}, Locale.getDefault()), response);
    }

    public BaseResponse<Void> deleteById(long id)
    {
        employeeService.deleteById(id);
        return new BaseResponse<>(ResultCode.SUCCESS.getValue(),
                messageSource.getMessage("api.successfully.deleted.response", new String[] {}, Locale.getDefault()));
    }

    public BaseResponse<EmployeeResponseDTOList> findAll(int pageNumber, int pageSize)
    {
        Page<Employee> employeePage = employeeService.findAll(pageNumber, pageSize);

        EmployeeResponseDTOList response = new EmployeeResponseDTOList();
        Metadata metadata = Metadata.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();

        if(employeePage.isEmpty())
        {
            response.setMetadata(metadata);
            response.setEmployees(Collections.emptyList());

            return new BaseResponse<>(ResultCode.NO_DATA_FOUND.getValue(),
                    ResultCode.NO_DATA_FOUND.getName(),
                    response);
        }

        List<EmployeeResponseDTO> employeeResponseDTOList = employeeMapper.entityListToResponseList(employeePage.getContent());
        metadata.setTotalPages(employeePage.getTotalPages());
        metadata.setTotalRecords(employeePage.getTotalElements());

        response.setEmployees(employeeResponseDTOList);
        response.setMetadata(metadata);

        return new BaseResponse<>(ResultCode.SUCCESS.getValue(),
                messageSource.getMessage("api.successfully.fetched.response", new String[] {}, Locale.getDefault()),
                response);
    }
}
