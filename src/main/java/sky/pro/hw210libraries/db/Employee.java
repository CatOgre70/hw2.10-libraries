package sky.pro.hw210libraries.db;

import java.util.Objects;

public class Employee {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final int department;
    private final double salary;

    public Employee(String firstName, String middleName, String lastName, int department, double salary) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return department == employee.department && Double.compare(employee.salary, salary) == 0 &&
                firstName.equals(employee.firstName) && middleName.equals(employee.middleName) &&
                lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName, department, salary);
    }
}
