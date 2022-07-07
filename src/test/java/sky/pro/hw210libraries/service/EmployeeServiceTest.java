package sky.pro.hw210libraries.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import sky.pro.hw210libraries.db.Employee;
import sky.pro.hw210libraries.exception.EmployeeAlreadyAddedException;
import sky.pro.hw210libraries.exception.EmployeeNotFoundException;
import sky.pro.hw210libraries.exception.ErrorInNameException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static sky.pro.hw210libraries.service.Constants.*;

public class EmployeeServiceTest {

    EmployeeService employeeService = new EmployeeServiceImpl();

    @ParameterizedTest
    @MethodSource("provideWrongParamsForIllegalArgumentException")
    public void testAddEmployeeIllegalArgumentException(String firstName, String middleName,
                                                        String lastName, int department, double salary){
        assertThrows(IllegalArgumentException.class, () -> this.employeeService.addEmployee(firstName, middleName,
                lastName, department, salary));
    }

    @ParameterizedTest
    @MethodSource("provideWrongParamsForErrorInNameException")
    public void testAddEmployeeForErrorInNameException(String firstName, String middleName,
                                                       String lastName, int department, double salary){
        assertThrows(ErrorInNameException.class, () -> this.employeeService.addEmployee(firstName, middleName,
                lastName, department, salary));
    }

    @Test
    public void testAddEmployee(){
        Map<String, Employee> expected = new HashMap<>();
        Map<String, Employee> result = employeeService.allEmployeeList();
        String firstName = employeeService.normaliseNames(RIGHT_FIRST_NAME);
        String middleName = employeeService.normaliseNames(RIGHT_MIDDLE_NAME);
        String lastName = employeeService.normaliseNames(RIGHT_LAST_NAME);

        for (String s : result.keySet()) {
            expected.put(s, result.get(s));
        }
        String key = firstName + middleName + lastName;
        Employee e = new Employee(firstName, middleName, lastName, RIGHT_DEPARTMENT_NUMBER, RIGHT_SALARY);
        expected.put(key, e);
        employeeService.addEmployee(firstName, middleName, lastName, RIGHT_DEPARTMENT_NUMBER, RIGHT_SALARY);
        assertEquals(expected, result);
    }

    @Test
    public void testAddEmployeeForEmployeeAlreadyAddedException(){
        String firstName = employeeService.normaliseNames(RIGHT_FIRST_NAME);
        String middleName = employeeService.normaliseNames(RIGHT_MIDDLE_NAME);
        String lastName = employeeService.normaliseNames(RIGHT_LAST_NAME);
        employeeService.addEmployee(firstName, middleName, lastName, RIGHT_DEPARTMENT_NUMBER, RIGHT_SALARY);
        assertThrows(EmployeeAlreadyAddedException.class, () ->
                employeeService.addEmployee(firstName, middleName, lastName, RIGHT_DEPARTMENT_NUMBER, RIGHT_SALARY));
    }
    @ParameterizedTest
    @MethodSource("provideListForFindEmployee")
    public void testFindEmployee(String firstName, String middleName, String lastName){
        firstName = employeeService.normaliseNames(firstName);
        middleName = employeeService.normaliseNames(middleName);
        lastName = employeeService.normaliseNames(lastName);
        String key = firstName + middleName + lastName;
        Employee e = employeeService.findEmployee(firstName, middleName, lastName);
        assertEquals(key, e.getFirstName()+e.getMiddleName()+e.getLastName());
    }

    @Test
    public void testFindEmployeeForEmployeeNotFoundException(){
        String firstName = employeeService.normaliseNames(RIGHT_FIRST_NAME);
        String middleName = employeeService.normaliseNames(RIGHT_MIDDLE_NAME);
        String lastName = employeeService.normaliseNames(RIGHT_LAST_NAME);
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee(firstName, middleName, lastName));
    }

    @Test
    public void testRemoveEmployee(){
        String firstName = employeeService.normaliseNames("Иван");
        String middleName = employeeService.normaliseNames("Иванович");
        String lastName = employeeService.normaliseNames("Иванцов");
        String key = firstName + middleName + lastName;
        Employee e = employeeService.removeEmployee(firstName, middleName, lastName);
        assertEquals(key, e.getFirstName()+e.getMiddleName()+e.getLastName());
    }

    @Test
    public void testRemoveEmployeeForEmployeeNotFoundException(){
        String firstName = employeeService.normaliseNames(RIGHT_FIRST_NAME);
        String middleName = employeeService.normaliseNames(RIGHT_MIDDLE_NAME);
        String lastName = employeeService.normaliseNames(RIGHT_LAST_NAME);
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee(firstName, middleName, lastName));
    }

    private static Stream<Arguments> provideWrongParamsForIllegalArgumentException(){
        return Stream.of(
                Arguments.of(RIGHT_FIRST_NAME, RIGHT_MIDDLE_NAME, RIGHT_LAST_NAME, RIGHT_DEPARTMENT_NUMBER, WRONG_SALARY),
                Arguments.of(RIGHT_FIRST_NAME, RIGHT_MIDDLE_NAME, RIGHT_LAST_NAME, WRONG_DEPARTMENT_NUMBER, RIGHT_SALARY),
                Arguments.of(RIGHT_FIRST_NAME, RIGHT_MIDDLE_NAME, LAST_MANE_NULL, RIGHT_DEPARTMENT_NUMBER, RIGHT_SALARY),
                Arguments.of(RIGHT_FIRST_NAME, MIDDLE_NAME_NULL, RIGHT_LAST_NAME, RIGHT_DEPARTMENT_NUMBER, RIGHT_SALARY),
                Arguments.of(FIRST_NAME_NULL, RIGHT_MIDDLE_NAME, RIGHT_LAST_NAME, RIGHT_DEPARTMENT_NUMBER, RIGHT_SALARY)
        );
    }

    private static Stream<Arguments> provideWrongParamsForErrorInNameException(){
        return Stream.of(
                Arguments.of(RIGHT_FIRST_NAME, RIGHT_MIDDLE_NAME, WRONG_LAST_NAME, RIGHT_DEPARTMENT_NUMBER, RIGHT_SALARY),
                Arguments.of(RIGHT_FIRST_NAME, WRONG_MIDDLE_NAME, RIGHT_LAST_NAME, RIGHT_DEPARTMENT_NUMBER, RIGHT_SALARY),
                Arguments.of(WRONG_FIRST_NAME, RIGHT_MIDDLE_NAME, RIGHT_LAST_NAME, RIGHT_DEPARTMENT_NUMBER, RIGHT_SALARY)
        );
    }

    private static Stream<Arguments> provideListForFindEmployee(){
        return Stream.of(
                Arguments.of("Иван", "Иванович", "Иванцов"),
                Arguments.of( "Петр", "Иннокентьевич", "Петров"),
                Arguments.of("Семен", "Семенович", "Горбунков"),
                Arguments.of("Иннокентий", "Борисович", "Чулков"),
                Arguments.of("Сергей", "Ануфриевич", "Ябеда"),
                Arguments.of("Иосиф", "Вахтангович", "Гришин"),
                Arguments.of("Иван", "Иванович", "Иванов"),
                Arguments.of("Алибек", "Джунгарович", "Хомяков"),
                Arguments.of("Рустам", "Ибрагимович", "Сулейбеков"),
                Arguments.of("Зухра", "Петровна", "Джугашвилли"),
                Arguments.of("Казбек", "Дмитриевич", "Светлый"),
                Arguments.of("Сулейман", "Мыколович", "Беспамятный"),
                Arguments.of("Михайло", "Дмитриевич", "Главко"),
                Arguments.of("Елена", "Арменовна", "Акопян"),
                Arguments.of("Гюльчатай", "Зурабовна", "Сухова"),
                Arguments.of("Наталья", "Альбертовна", "Рабинович")
        );
    }

}
