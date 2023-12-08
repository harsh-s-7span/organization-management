package com.organization.management.controller;

import com.organization.management.dto.BaseResponse;
import com.organization.management.dto.request.EmployeeRequestDTO;
import com.organization.management.dto.response.EmployeeResponseDTO;
import com.organization.management.dto.response.EmployeeResponseDTOList;
import com.organization.management.handler.EmployeeHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeHandler employeeHandler;

    public EmployeeController(EmployeeHandler employeeHandler)
    {
        this.employeeHandler = employeeHandler;
    }

    @PostMapping
    public BaseResponse<EmployeeResponseDTO> save(@Valid @RequestBody EmployeeRequestDTO request)
    {
        return employeeHandler.save(request);
    }

    @GetMapping("/{id}")
    public BaseResponse<EmployeeResponseDTO> view(@PathVariable long id)
    {
        return employeeHandler.view(id);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteById(@PathVariable long id)
    {
        return employeeHandler.deleteById(id);
    }

    @GetMapping
    public BaseResponse<EmployeeResponseDTOList> findAll(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize)
    {
        return employeeHandler.findAll(pageNumber, pageSize);
    }
}
