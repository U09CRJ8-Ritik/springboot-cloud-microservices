package com.practice.employeeService.service;

import com.practice.employeeService.model.APIResponseDto;
import com.practice.employeeService.model.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long id);
}
