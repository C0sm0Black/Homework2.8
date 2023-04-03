package pro.sky.homework28.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.homework28.domain.Employee;
import pro.sky.homework28.exception.EmployeeAlreadyAddedException;
import pro.sky.homework28.exception.EmployeeNotFoundException;
import pro.sky.homework28.exception.EmployeeStorageIsFullException;
import pro.sky.homework28.exception.WrongEmployeeDataException;

import java.util.ArrayList;
import java.util.List;

import static pro.sky.homework28.domain.Department.DEPARTMENT_BY_ID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int MAX_COUNT_EMPLOYEES = 13;
    private static List<Employee> employees = new ArrayList<>();

    static {

        Employee emp1 = new Employee("Виктория", "Гончарова", 63_000, DEPARTMENT_BY_ID.get(1));
        Employee emp2 = new Employee("Олег", "Коновалов", 48_150, DEPARTMENT_BY_ID.get(1));

        Employee emp3 = new Employee("Анна", "Комаева", 55_000, DEPARTMENT_BY_ID.get(2));
        Employee emp4 = new Employee("Андрей", "Рак", 78_045, DEPARTMENT_BY_ID.get(2));

        Employee emp5 = new Employee("Рустам", "Магамедов", 71_060, DEPARTMENT_BY_ID.get(3));
        Employee emp6 = new Employee("Василиса", "Евклидовна", 46_005, DEPARTMENT_BY_ID.get(3));

        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);
        employees.add(emp6);

    }

    public Employee add(String firstName, String lastName, double salary, int id) {

        checkFirstNameAndLastNameForEmpty(firstName, lastName);
        checkFirstNameAndLastNameForUpperCase(firstName, lastName);
        checkFirstNameAndLastNameForAlpha(firstName, lastName);

        if (employees.size() > MAX_COUNT_EMPLOYEES - 1) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }

        Employee employee = new Employee(firstName, lastName, salary, DEPARTMENT_BY_ID.get(id));

        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("В массиве уже есть такой сотрудник");
        }

        employees.add(employee);
        return employee;

    }

    public Employee find(String firstName, String lastName) {

        checkFirstNameAndLastNameForEmpty(firstName, lastName);
        checkFirstNameAndLastNameForUpperCase(firstName, lastName);
        checkFirstNameAndLastNameForAlpha(firstName, lastName);

        return employees.stream()
                .filter(e -> e.getFirstName().equals(firstName) && e.getLastName().equals(lastName))
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));

    }

    public Employee remove(String firstName, String lastName) {

        checkFirstNameAndLastNameForEmpty(firstName, lastName);
        checkFirstNameAndLastNameForUpperCase(firstName, lastName);
        checkFirstNameAndLastNameForAlpha(firstName, lastName);

        Employee employee = find(firstName, lastName);
        employees.removeIf(e -> e.equals(employee));
        return employee;

    }

    public List<Employee> getAll() {
        return employees;
    }

    private void checkFirstNameAndLastNameForEmpty(String firstName, String lastName) {

        if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName)) {
            throw new WrongEmployeeDataException("Имя или фамилия не могут быть пустыми!");
        }

    }

    private void checkFirstNameAndLastNameForUpperCase(String firstName, String lastName) {

        if (!firstName.equals(StringUtils.capitalize(StringUtils.lowerCase(firstName)))
                || !lastName.equals(StringUtils.capitalize(StringUtils.lowerCase(lastName)))) {
            throw new WrongEmployeeDataException("Имя или фамилия должны начинатся с заглавной буквы," +
                    " и далее следовать маленькие!");
        }

    }

    private void checkFirstNameAndLastNameForAlpha(String firstName, String lastName) {

        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new WrongEmployeeDataException("Имя или фамилия должны содержать только буквы!");
        }

    }

}
