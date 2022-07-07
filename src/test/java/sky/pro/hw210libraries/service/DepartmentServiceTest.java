package sky.pro.hw210libraries.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sky.pro.hw210libraries.db.Employee;

import java.util.*;

import static org.mockito.Mockito.when;
import static sky.pro.hw210libraries.service.Constants.RIGHT_DEPARTMENT_NUMBER;
import static sky.pro.hw210libraries.service.Constants.WRONG_DEPARTMENT_NUMBER;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeServiceImpl employeeServiceMock;

    @InjectMocks
    private DepartmentServiceImpl out;

    private final Map<String, Employee> allEmployeeList = new HashMap<>();

    private final List<Employee> department5List = new ArrayList<>();

    private final Map<Integer, Set<Employee>> allDepartmentsEmployeeList = new HashMap<>();

    @BeforeEach
    void variablesInitialization(){
        allEmployeeList.put("ИванИвановичИванцов", new Employee("Иван", "Иванович", "Иванцов", 0, 50_000));
        allEmployeeList.put("ПетрИннокентьевичПетров", new Employee("Петр", "Иннокентьевич", "Петров", 0, 150_000));
        allEmployeeList.put("СеменСеменовичГорбунков", new Employee("Семен", "Семенович", "Горбунков", 1, 101_000));
        allEmployeeList.put("ИннокентийБорисовичЧулков", new Employee("Иннокентий", "Борисович", "Чулков", 1, 120_000));
        allEmployeeList.put("СергейАнуфриевичЯбеда", new Employee("Сергей", "Ануфриевич", "Ябеда", 2, 40_000));
        allEmployeeList.put("ИосифВахтанговичГришин", new Employee("Иосиф", "Вахтангович", "Гришин", 2, 91_000));
        allEmployeeList.put("ИванИвановичИванов", new Employee("Иван", "Иванович", "Иванов", 3, 50_000));
        allEmployeeList.put("АлибекДжунгаровичХомяков", new Employee("Алибек", "Джунгарович", "Хомяков", 3, 42_000));
        allEmployeeList.put("РустамИбрагимовичСулейбеков", new Employee("Рустам", "Ибрагимович", "Сулейбеков", 4, 22_000));
        allEmployeeList.put("ЗухраПетровнаДжугашвилли", new Employee("Зухра", "Петровна", "Джугашвилли", 4, 173_000));
        allEmployeeList.put("КазбекДмитриевичСветлый", new Employee("Казбек", "Дмитриевич", "Светлый", 5, 17_500));
        allEmployeeList.put("СулейманМыколовичБеспамятный", new Employee("Сулейман", "Мыколович", "Беспамятный", 5, 23_700));
        allEmployeeList.put("МихайлоДмитриевичГлавко", new Employee("Михайло", "Дмитриевич", "Главко", 6, 110_300));
        allEmployeeList.put("ЕленаАрменовнаАкопян", new Employee("Елена", "Арменовна", "Акопян", 6, 199_999.99));
        allEmployeeList.put("ГюльчатайЗурабовнаСухова", new Employee("Гюльчатай", "Зурабовна", "Сухова", 7, 69_000));
        allEmployeeList.put("НатальяАльбертовнаРабинович", new Employee("Наталья", "Альбертовна", "Рабинович", 7, 29_794));

        department5List.add(new Employee("Казбек", "Дмитриевич", "Светлый", 5, 17_500));
        department5List.add(new Employee("Сулейман", "Мыколович", "Беспамятный", 5, 23_700));

        Set<Employee> set0 = new HashSet<>();
        set0.add(new Employee("Иван", "Иванович", "Иванцов", 0, 50_000));
        set0.add(new Employee("Петр", "Иннокентьевич", "Петров", 0, 150_000));
        allDepartmentsEmployeeList.put(0, set0);

        Set<Employee> set1 = new HashSet<>();
        set1.add(new Employee("Семен", "Семенович", "Горбунков", 1, 101_000));
        set1.add(new Employee("Иннокентий", "Борисович", "Чулков", 1, 120_000));
        allDepartmentsEmployeeList.put(1, set1);

        Set<Employee> set2 = new HashSet<>();
        set2.add(new Employee("Сергей", "Ануфриевич", "Ябеда", 2, 40_000));
        set2.add(new Employee("Иосиф", "Вахтангович", "Гришин", 2, 91_000));
        allDepartmentsEmployeeList.put(2, set2);

        Set<Employee> set3 = new HashSet<>();
        set3.add(new Employee("Иван", "Иванович", "Иванов", 3, 50_000));
        set3.add(new Employee("Алибек", "Джунгарович", "Хомяков", 3, 42_000));
        allDepartmentsEmployeeList.put(3, set3);

        Set<Employee> set4 = new HashSet<>();
        set4.add(new Employee("Рустам", "Ибрагимович", "Сулейбеков", 4, 22_000));
        set4.add(new Employee("Зухра", "Петровна", "Джугашвилли", 4, 173_000));
        allDepartmentsEmployeeList.put(4, set4);

        Set<Employee> set5 = new HashSet<>();
        set5.add(new Employee("Казбек", "Дмитриевич", "Светлый", 5, 17_500));
        set5.add(new Employee("Сулейман", "Мыколович", "Беспамятный", 5, 23_700));
        allDepartmentsEmployeeList.put(5, set5);

        Set<Employee> set6 = new HashSet<>();
        set6.add(new Employee("Михайло", "Дмитриевич", "Главко", 6, 110_300));
        set6.add(new Employee("Елена", "Арменовна", "Акопян", 6, 199_999.99));
        allDepartmentsEmployeeList.put(6, set6);

        Set<Employee> set7 = new HashSet<>();
        set7.add(new Employee("Гюльчатай", "Зурабовна", "Сухова", 7, 69_000));
        set7.add(new Employee("Наталья", "Альбертовна", "Рабинович", 7, 29_794));
        allDepartmentsEmployeeList.put(7, set7);

    }

    @Test
    public void testFindEmployeeWithMaxSalary(){
        when(employeeServiceMock.allEmployeeList()).thenReturn(allEmployeeList);
        Employee actual = new Employee("Сулейман", "Мыколович", "Беспамятный", 5, 23_700);
        Employee result = out.findEmployeeWithMaxSalary(RIGHT_DEPARTMENT_NUMBER);
        Assertions.assertEquals(actual, result);
    }

    @Test
    public void testFindEmployeeWithMaxSalaryForRuntimeException(){
        when(employeeServiceMock.allEmployeeList()).thenReturn(allEmployeeList);
        Assertions.assertThrows(RuntimeException.class, () -> out.findEmployeeWithMaxSalary(WRONG_DEPARTMENT_NUMBER));
    }

    @Test
    public void testFindEmployeeWithMinSalary(){
        when(employeeServiceMock.allEmployeeList()).thenReturn(allEmployeeList);
        Employee actual = new Employee("Казбек", "Дмитриевич", "Светлый", 5, 17_500);
        Employee result = out.findEmployeeWithMinSalary(RIGHT_DEPARTMENT_NUMBER);
        Assertions.assertEquals(actual, result);
    }

    @Test
    public void testFindEmployeeWithMinSalaryForRuntimeException(){
        when(employeeServiceMock.allEmployeeList()).thenReturn(allEmployeeList);
        Assertions.assertThrows(RuntimeException.class, () -> out.findEmployeeWithMinSalary(WRONG_DEPARTMENT_NUMBER));
    }

    @Test
    public void testDepartmentEmployeeList(){
        Comparator<Employee> employeeComparator = Comparator.comparing(Employee::getLastName);
        when(employeeServiceMock.allEmployeeList()).thenReturn(allEmployeeList);
        List<Employee> actual = department5List;
        actual.sort(employeeComparator);
        List<Employee> result = out.departmentEmployeeList(RIGHT_DEPARTMENT_NUMBER);
        result.sort(employeeComparator);
        Assertions.assertEquals(actual, result);
    }

    @Test
    public void testDepartmentEmployeeListForEmptyList(){
        when(employeeServiceMock.allEmployeeList()).thenReturn(allEmployeeList);
        Assertions.assertTrue(out.departmentEmployeeList(WRONG_DEPARTMENT_NUMBER).isEmpty());
    }

    @Test
    public void testAllDepartmentsEmployeeList(){
        when(employeeServiceMock.allEmployeeList()).thenReturn(allEmployeeList);
        Map<Integer, Set<Employee>> result = out.allDepartmentsEmployeeList();
        Assertions.assertEquals(allDepartmentsEmployeeList, result);
    }

}
