package com.learnwebclient.service;

import com.learnwebclient.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

import static com.learnwebclient.constants.EmployeeConstants.GET_ALL_EMP_V1;
import static com.learnwebclient.constants.EmployeeConstants.GET_EMP_BY_ID_V1;

@Slf4j
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
        try {
            return webClient.get().uri(GET_EMP_BY_ID_V1, empId)
                    .retrieve()
                    .bodyToMono(Employee.class)
                    .block();
        }catch (WebClientResponseException ex) {
            log.error(
                    "Response code is {} and the response body is {}",
                    ex.getRawStatusCode(),
                    ex.getResponseBodyAsString()
            );
            log.error(ex.getMessage());
            log.error("StackTrace: {}", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("StackTrace: {}", ex);
            throw ex;
        }
    }

}
