package sky.pro.hw210libraries.service;

import sky.pro.hw210libraries.db.Employee;

import java.util.Map;

public interface EmployeeService {
    Employee addEmployee(String firstName, String middleName, String lastName, int department, double salary);
    Employee removeEmployee(String firstName, String middleName, String lastName);
    Employee findEmployee(String firstName, String middleName, String lastName);
    Map<String, Employee> allEmployeeList();

    boolean validateInputData(String firstName, String middleName, String lastName, int department, double salary);
    String normaliseNames(String name);
}
