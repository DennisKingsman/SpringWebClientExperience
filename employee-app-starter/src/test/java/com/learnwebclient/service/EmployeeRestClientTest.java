package com.learnwebclient.service;

import com.learnwebclient.dto.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeRestClientTest {

    private static final String BASE_URL = "http://localhost:8081/employeeservice";
    private WebClient webClient = WebClient.create(BASE_URL);
    private EmployeeRestClient employeeRestClient = new EmployeeRestClient(webClient);

    @Test
    void getAllEmpTest() {
        List<Employee> employees = employeeRestClient.getAllEmp();
        assertTrue(employees.size() > 0);
    }

}
