package com.example.shared.models;

import java.time.LocalDateTime;

public class AuditEvent {
    private String host;
    private String requestId;
    private String sessionId;
    private String name;
    private String serviceContract;
    private String operation;
    private String operationHandler;
    private String request;
    private String response;
    private LocalDateTime requestInTime;
    private LocalDateTime responseOutTime;

    public AuditEvent() {
        setHost("");
        setRequestId("");
        setSessionId("");
        setName("");
        setServiceContract("");
        setOperation("");
        setOperationHandler("");
        setRequest("");
        setResponse("");
        setRequestInTime(LocalDateTime.MIN);
        setResponseOutTime(LocalDateTime.MIN);
    }

    @Override
    public String toString() {
        return "\"auditEvent\":{" +
                "\"host\":\"" + getHost() +
                "\", \"requestId\":\"" + getRequestId() +
                "\", \"sessionId\":\"" + getSessionId() +
                "\", \"name\":\"" + getName() +
                "\", \"serviceContract\":\"" + getServiceContract() +
                "\", \"operation\":\"" + getOperation() +
                "\", \"operationHandler\":\"" + getOperationHandler() +
                "\", \"request\":\"" + getRequest() +
                "\", \"response\":\"" + getResponse() +
                "\", \"requestInTime\":\"" + getRequestInTime().toString() +
                "\", \"responseOutTime\":\"" + getResponseOutTime().toString() +
                "\"" +
                '}';
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDateTime getRequestInTime() {
        return requestInTime;
    }

    public void setRequestInTime(LocalDateTime requestInTime) {
        this.requestInTime = requestInTime;
    }

    public LocalDateTime getResponseOutTime() {
        return responseOutTime;
    }

    public void setResponseOutTime(LocalDateTime responseOutTime) {
        this.responseOutTime = responseOutTime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getServiceContract() {
        return serviceContract;
    }

    public void setServiceContract(String serviceContract) {
        this.serviceContract = serviceContract;
    }

    public String getOperationHandler() {
        return operationHandler;
    }

    public void setOperationHandler(String operationHandler) {
        this.operationHandler = operationHandler;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
