package com.practice.employeeService.controller;

import com.practice.employeeService.model.APIResponseDto;
import com.practice.employeeService.model.EmployeeDto;
import com.practice.employeeService.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    // Build Save Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Build Get Employee By ID REST API
    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable("id") Long id) {

        APIResponseDto apiResponseDto = employeeService.getEmployeeById(id);

        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);

    }
}
