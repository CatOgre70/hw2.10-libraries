package sky.pro.hw210libraries.model;

import org.apache.commons.lang3.StringUtils;

public class Employee {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final int department;
    private final double salary;

    public Employee(String firstName, String middleName, String lastName, int department, double salary) {
        if(firstName == null || middleName == null || lastName == null || department < 0 || department > 7 || salary < 0){
            throw new IllegalArgumentException("Illegal parameters in Employee class constructor");
        }
        if(!(StringUtils.isAlpha(firstName) && StringUtils.isAlpha(middleName) && StringUtils.isAlpha(lastName))){
            throw new ErrorInNameException("Illegal first, middle or last name: they should contain only alphabet characters");
        }

        firstName = StringUtils.lowerCase(firstName);
        middleName = StringUtils.lowerCase(middleName);
        lastName = StringUtils.lowerCase(lastName);
        firstName = StringUtils.capitalize(firstName);
        middleName = StringUtils.capitalize(middleName);
        lastName = StringUtils.capitalize(lastName);

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

}
