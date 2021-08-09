package com.learnwebclient.service;

import com.learnwebclient.dto.Employee;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.learnwebclient.constants.EmployeeConstants.GET_ALL_EMP_V1;
import static com.learnwebclient.constants.EmployeeConstants.GET_EMP_BY_ID_V1;

public class EmployeeRestClient {

    private WebClient webClient;

    public EmployeeRestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Employee> getAllEmp() {
        return webClient.get().uri(GET_ALL_EMP_V1)
                .retrieve()
                .bodyToFlux(Employee.class)
                .collectList()
                .block();
    }

    public Employee getEmpById(int empId) {
        return webClient.get().uri(GET_EMP_BY_ID_V1, empId)
                .retrieve()
                .bodyToMono(Employee.class)
                .block();
    }

}
