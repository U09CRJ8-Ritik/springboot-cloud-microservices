package com.practice.employeeService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponseDto {
    private EmployeeDto employeeDto;

    private DepartmentDto departmentDto;
}
