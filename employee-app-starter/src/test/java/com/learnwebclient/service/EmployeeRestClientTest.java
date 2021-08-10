package com.learnwebclient.service;

import com.learnwebclient.dto.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRestClientTest {

    private static final String BASE_URL = "http://localhost:8081/employeeservice";
    private WebClient webClient = WebClient.create(BASE_URL);
    private EmployeeRestClient employeeRestClient = new EmployeeRestClient(webClient);

    @Test
    void getAllEmpTest() {
        List<Employee> employees = employeeRestClient.getAllEmp();
        for (Employee emp: employees) {
            System.out.println(emp.toString());
        }
        assertTrue(employees.size() > 0);
    }

    @Test
    void getEmpByIdTest() {
        Long empId = 1L;
        Employee actual = employeeRestClient.getEmpById(empId);
        assertEquals("Chris", actual.getFirstName());
    }

    @Test
    void getEmpByIdNotFoundTest() {
        Long empId = 10L;
        assertThrows(
                WebClientResponseException.class,
                () -> employeeRestClient.getEmpById(empId)
        );
    }

    @Test
    void getEmpsByNameTest() {
        String name = "Chris";
        List<Employee> employees = employeeRestClient.getEmpsByName(name);
        assertTrue(employees.size() > 0);
        Employee actual = employees.get(0);
        assertEquals("Chris", actual.getFirstName());
    }

    @Test
    void getEmpsByNameNotFoundTest() {
        String name = "NotChris";
        assertThrows(
                WebClientResponseException.class,
                () -> employeeRestClient.getEmpsByName(name)
        );
    }

    @Test
    void postNewEmpTest() {
        Employee employee = new Employee();
        employee.setId(12L);
        employee.setAge(54);
        employee.setFirstName("Irak");
        employee.setGender("Male");
        employee.setLastName("Habib");
        employee.setRole("dev");

        Employee expected = employeeRestClient.postNewEmp(employee);
        System.out.print(expected.getFirstName());
        assertNotNull(expected.getId());
    }

    @Test
    void postNewEmpBadRequestTest() {
        Employee employee = new Employee();
        employee.setId(12L);
        employee.setAge(54);
        employee.setFirstName(null);
        employee.setGender("Male");
        employee.setLastName("Habib");
        employee.setRole("dev");

        assertThrows(
                WebClientResponseException.class,
                () -> employeeRestClient.postNewEmp(employee)
        );
    }

    @Test
    void updateEmpTest() {
        Employee employee = new Employee();
        employee.setId(2L);
        employee.setFirstName("new Name");
        employee.setLastName("new LastName");
        employee.setGender("Male");
        employee.setAge(54);
        employee.setRole("dev");

        Employee updatedEmp = employeeRestClient.updateEmp(employee.getId(), employee);
        assertEquals("new Name", updatedEmp.getFirstName());
        assertEquals("new LastName", updatedEmp.getLastName());
    }

    @Test
    void updateEmpErrorTest() {
        Employee employee = new Employee();
        employee.setId(122L);
        employee.setFirstName("new Name");
        employee.setLastName("new LastName");
        employee.setGender("Male");
        employee.setAge(54);
        employee.setRole("dev");

        assertThrows(
                WebClientResponseException.class,
                () -> employeeRestClient
                        .updateEmp(
                                employee.getId(),
                                employee)
        );
    }

    @Test
    void deleteEmpByIdTest() {
        String expected = "Employee deleted successfully.";
        String actualResponse = employeeRestClient.deleteEmpById(6L);
        assertEquals(expected, actualResponse);
    }

}
