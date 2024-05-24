package EmployWise.EmployeeDatabase.repo;

import EmployWise.EmployeeDatabase.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, UUID> {
}
