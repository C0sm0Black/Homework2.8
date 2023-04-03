package pro.sky.homework28.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sky.homework28.domain.Department;
import pro.sky.homework28.domain.Employee;
import pro.sky.homework28.exception.EmployeeAlreadyAddedException;
import pro.sky.homework28.exception.EmployeeNotFoundException;
import pro.sky.homework28.exception.EmployeeStorageIsFullException;
import pro.sky.homework28.exception.WrongEmployeeDataException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pro.sky.homework28.domain.Department.DEPARTMENT_BY_ID;

@ContextConfiguration(classes = EmployeeServiceImpl.class)
@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void add_success() {

        //Подготовка входных данных

        String firstName = "Олег";
        String lastName = "Круглов";
        Double salary = 25_000d;
        Department department = DEPARTMENT_BY_ID.get(1);

        //Подготовка ожидаемого результата

        Employee expected = new Employee(firstName, lastName, salary, department);

        //Начало теста

        Employee actual = employeeService.add(firstName, lastName, salary, 1);
        assertEquals(expected, actual);

    }

    @Test
    void add_withWrongEmployeeDataException1() {

        //Подготовка входных данных

        String firstName = "";
        String lastName = "Круглов";
        Double salary = 25_000d;
        Department department = DEPARTMENT_BY_ID.get(1);

        //Подготовка ожидаемого результата

        String expectedMessage = "Имя или фамилия не могут быть пустыми!";

        //Начало теста

        Exception exception = assertThrows(WrongEmployeeDataException.class,
                () -> employeeService.add(firstName, lastName, salary, 1));

        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    void add_withWrongEmployeeDataException2() {

        //Подготовка входных данных

        String firstName = "оЛЕГ";
        String lastName = "Круглов";
        Double salary = 25_000d;
        Department department = DEPARTMENT_BY_ID.get(1);

        //Подготовка ожидаемого результата

        String expectedMessage = "Имя или фамилия должны начинатся с заглавной буквы," +
                " и далее следовать маленькие!";

        //Начало теста

        Exception exception = assertThrows(WrongEmployeeDataException.class,
                () -> employeeService.add(firstName, lastName, salary, 1));

        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    void add_withWrongEmployeeDataException3() {

        //Подготовка входных данных

        String firstName = "Ацу1";
        String lastName = "Ав2233фвв";
        Double salary = 25_000d;
        Department department = DEPARTMENT_BY_ID.get(1);

        //Подготовка ожидаемого результата

        String expectedMessage = "Имя или фамилия должны содержать только буквы!";

        //Начало теста

        Exception exception = assertThrows(WrongEmployeeDataException.class,
                () -> employeeService.add(firstName, lastName, salary, 1));

        assertEquals(expectedMessage, exception.getMessage());

    }


    @Test
    void add_withEmployeeStorageIsFullException() {

        //Подготовка входных данных

        employeeService.add("Валера", "Красный", 45_000, 1);
        employeeService.add("Валераа", "Красный", 45_000, 1);
        employeeService.add("Валерааа", "Красный", 45_000, 1);
        employeeService.add("Валераааа", "Красный", 45_000, 1);
        employeeService.add("Валерааааа", "Красный", 45_000, 1);
        employeeService.add("Валераааааа", "Красный", 45_000, 1);
        employeeService.add("Валерааааааа", "Красный", 45_000, 1);

        String firstName = "Олег";
        String lastName = "Круглов";
        Double salary = 25_000d;
        Department department = DEPARTMENT_BY_ID.get(1);

        //Подготовка ожидаемого результата

        String expectedMessage = "Массив сотрудников переполнен";

        //Начало теста

        Exception exception = assertThrows(EmployeeStorageIsFullException.class,
                () -> employeeService.add(firstName, lastName, salary, 1));

        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    void add_withEmployeeAlreadyAddedException() {

        //Подготовка входных данных

        String firstName = "Олег";
        String lastName = "Коновалов";
        Double salary = 48_150d;
        Department department = DEPARTMENT_BY_ID.get(1);

        //Подготовка ожидаемого результата

        String expectedMessage = "В массиве уже есть такой сотрудник";

        //Начало теста

        Exception exception = assertThrows(EmployeeAlreadyAddedException.class,
                () -> employeeService.add(firstName, lastName, salary, 1));

        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    void find_success() {

        //Подготовка входных данных

        String firstName = "Рустам";
        String lastName = "Магамедов";
        Double salary = 71_060d;
        Department department = DEPARTMENT_BY_ID.get(3);

        //Подготовка ожидаемого результата

        Employee expected = new Employee(firstName, lastName, salary, department);

        //Начало теста

        Employee actual = employeeService.find(firstName, lastName);
        assertEquals(expected, actual);

    }

    @Test
    void find_withEmployeeNotFoundException() {

        //Подготовка входных данных

        String firstName = "Павеля";
        String lastName = "Глушко";
        Double salary = 63_000d;
        Department department = DEPARTMENT_BY_ID.get(1);

        //Подготовка ожидаемого результата

        String expectedMessage = "Сотрудник не найден";

        //Начало теста

        Exception exception = assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.find(firstName, lastName)
        );

        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    void remove_success() {

        //Подготовка входных данных

        String firstName = "Виктория";
        String lastName = "Гончарова";
        Double salary = 63_000d;
        Department department = DEPARTMENT_BY_ID.get(1);

        //Подготовка ожидаемого результата

        Employee expected = new Employee(firstName, lastName, salary, department);

        //Начало теста

        Employee actual = employeeService.remove(firstName, lastName);
        assertEquals(expected, actual);

    }

    @Test
    void getAll() {

        //Подготовка входных данных

        List<Employee> employees = new ArrayList<>();

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

        //Подготовка ожидаемого результата

        List<Employee> expected = employees;

        //Начало теста

        List<Employee> actual = employeeService.getAll();
        assertEquals(expected, actual);

    }

}