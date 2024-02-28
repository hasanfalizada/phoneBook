package com.hasanalizada.phonebook.model;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String department;
    private String position;
    private String ipNumber;
    private String structure1;
    private String structure2;
    private String structure3;
    private String structure4;
    private String structure5;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        ACTIVE, DELETED
    }

    public Employee() {
        // Default constructor required by JPA
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIpNumber() {
        return ipNumber;
    }

    public void setIpNumber(String ipNumber) {
        this.ipNumber = ipNumber;
    }

    public String getStructure1() {
        return structure1;
    }

    public void setStructure1(String structure1) {
        this.structure1 = structure1;
    }

    public String getStructure2() {
        return structure2;
    }

    public void setStructure2(String structure2) {
        this.structure2 = structure2;
    }

    public String getStructure3() {
        return structure3;
    }

    public void setStructure3(String structure3) {
        this.structure3 = structure3;
    }

    public String getStructure4() {
        return structure4;
    }

    public void setStructure4(String structure4) {
        this.structure4 = structure4;
    }

    public String getStructure5() {
        return structure5;
    }

    public void setStructure5(String structure5) {
        this.structure5 = structure5;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
