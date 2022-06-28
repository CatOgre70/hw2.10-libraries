package sky.pro.hw210libraries.model;

import java.util.Map;

public interface EmployeeService {
    Employee addEmployee(String firstName, String middleName, String lastName, int department, double salary);
    Employee removeEmployee(String firstName, String middleName, String lastName);
    Employee findEmployee(String firstName, String middleName, String lastName);
    Map<String, Employee> allEmployeeList();
}
