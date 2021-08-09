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
        assertTrue(employees.size() > 0);
    }

    @Test
    void getEmpByIdTest() {
        int empId = 1;
        Employee actual = employeeRestClient.getEmpById(empId);
        assertEquals("Chris", actual.getFirstName());
    }

    @Test
    void getEmpByIdNotFoundTest() {
        int empId = 10;
        assertThrows(
                WebClientResponseException.class,
                () -> employeeRestClient.getEmpById(empId)
        );
    }

}
