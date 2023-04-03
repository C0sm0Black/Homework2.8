package pro.sky.homework28.service;

import pro.sky.homework28.domain.Employee;

import java.util.List;

public interface EmployeeService {

    Employee add(String firstName, String lastName, double salary, int id);
    Employee find(String firstName, String lastName);
    Employee remove(String firstName, String lastName);
    List<Employee> getAll();

}
