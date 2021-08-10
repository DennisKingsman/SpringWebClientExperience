package com.learnwebclient.service;

import com.learnwebclient.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static com.learnwebclient.constants.EmployeeConstants.*;

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

    public Employee getEmpById(Long empId) {
        try {
            return webClient.get().uri(GET_EMP_BY_ID_V1, empId)
                    .retrieve()
                    .bodyToMono(Employee.class)
                    .block();
        } catch (WebClientResponseException ex) {
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

    public List<Employee> getEmpsByName(String employeeName) {
        //?
        String uri = UriComponentsBuilder.fromUriString(GET_EMPS_BY_NAME_V1)
                        .queryParam(
                                "employee_name",
                                employeeName
                        ).build().toUriString();
        try {
            return webClient.get().uri(uri)
                    .retrieve()
                    .bodyToFlux(Employee.class)
                    .collectList()
                    .block();
        } catch (WebClientResponseException ex) {
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

    public Employee postNewEmp(Employee employee) {
        try {
            return webClient.post().uri(POST_NEW_EMP_V1)
                    .syncBody(employee)
                    .retrieve()
                    .bodyToMono(Employee.class)
                    .block();
        } catch (WebClientResponseException ex) {
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

    public Employee updateEmp(Long empId, Employee employee) {
        try {
            return webClient.put().uri(GET_EMP_BY_ID_V1, empId)
                    .syncBody(employee)
                    .retrieve()
                    .bodyToMono(Employee.class)
                    .block();
        } catch (WebClientResponseException ex) {
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
