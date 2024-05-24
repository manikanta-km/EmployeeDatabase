package EmployWise.EmployeeDatabase.service;

import EmployWise.EmployeeDatabase.model.Employee;
import EmployWise.EmployeeDatabase.repo.EmployeeRepo;
import EmployWise.EmployeeDatabase.service.emailutility.EmailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepo employeeRepo;

    public UUID addEmployee(Employee employee) {
        Employee savedEmployee = employeeRepo.save(employee);
        sendEmailToLevel1Manager(savedEmployee);
        return savedEmployee.getId();
    }

    public void sendEmailToLevel1Manager(Employee newEmployee){
        UUID managerId = newEmployee.getReportsTo();
        if(managerId != null){
            Employee manager = employeeRepo.findById(managerId).orElseThrow();
            String managerEmail = manager.getEmail();
            String employeeName = newEmployee.getEmployeeName();
            String employeePhoneNumber = newEmployee.getPhoneNumber();
            String employeeEmail = newEmployee.getEmail();
            EmailHandler.sendEmailToManager(managerEmail, employeeName, employeePhoneNumber, employeeEmail);
        }
    }

    public List<Employee> getAllEmployees(int page, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy));
        return employeeRepo.findAll(pageable).getContent();
    }

    public String updateEmployee(UUID id, String name, String phoneNumber, String email, UUID reportsTo, String profileImage ) {
        boolean flag = false;
        Employee existingEmployee = employeeRepo.findById(id).orElseThrow();
        if(name != null){
            existingEmployee.setEmployeeName(name);
            flag = true;
        }
        if(phoneNumber != null){
            existingEmployee.setPhoneNumber(phoneNumber);
            flag = true;
        }
        if(email != null){
            existingEmployee.setEmail(email);
            flag = true;
        }
        if(reportsTo != null){
            existingEmployee.setReportsTo(reportsTo);
            flag = true;
        }
        if(profileImage != null){
            existingEmployee.setProfileImage(profileImage);
            flag = true;
        }
        employeeRepo.save(existingEmployee);
        if(flag)
        return "Employee details updated successfully";
        else
            return "No fields are updated please update at least one field";
    }

    public String deleteEmployee(UUID id) {
        Employee existingEmployee = employeeRepo.findById(id).orElseThrow();
        employeeRepo.delete(existingEmployee);
        return "Employee deleted successfully";
    }

    public String getNthLevelManagerName(UUID employeeId, int level) {
        Employee employee = employeeRepo.findById(employeeId).orElseThrow();
        UUID managerId = employee.getReportsTo();
        while (level > 1 && managerId != null) {
            Employee manager = employeeRepo.findById(managerId).orElseThrow();
            if (manager == null) break;
            managerId = manager.getReportsTo();
            level--;
        }
        if (managerId != null) {
            Employee manager = employeeRepo.findById(managerId).orElseThrow();
            return manager.getEmployeeName();
        } else {
            return "No manager exists for the given employee at given level"; // case where there is no manager
        }
    }
}
