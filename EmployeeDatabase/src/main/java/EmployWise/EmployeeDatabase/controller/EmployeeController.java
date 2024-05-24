package EmployWise.EmployeeDatabase.controller;

import EmployWise.EmployeeDatabase.model.Employee;
import EmployWise.EmployeeDatabase.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("employee")
    public UUID addEmployee(@Valid @RequestBody Employee employee) {
        return  employeeService.addEmployee(employee);
    }

    @GetMapping("employees")
    public List<Employee> getAllEmployees(@RequestParam int page, @RequestParam int pageSize, @RequestParam String sortBy) {
        return employeeService.getAllEmployees(page, pageSize, sortBy);

    }

    @PutMapping("byId")
    public String updateEmployee(@RequestParam UUID id, @RequestParam (required = false) String name, @RequestParam (required = false) String phoneNumber, @RequestParam (required = false) String email, @RequestParam (required = false) UUID reportsTo, @RequestParam (required = false) String profileImage) {
       return employeeService.updateEmployee(id, name, phoneNumber, email, reportsTo, profileImage);
    }

    @DeleteMapping("byId")
    public String deleteEmployee(@RequestParam UUID id) {
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("nThLevelManager")
    public String nThLevelManager(@RequestParam UUID employeeId, @RequestParam int level){
        return employeeService.getNthLevelManagerName(employeeId, level);
    }
}
