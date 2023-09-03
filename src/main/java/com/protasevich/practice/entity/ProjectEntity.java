package com.protasevich.practice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@Data
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    @DateTimeFormat(fallbackPatterns = "dd.MM.yyyy")
    private String Date;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            mappedBy = "project")
    private List<EmployeeEntity> employees;

    public List<EmployeeEntity> getEmployees() {
        if (employees == null) {
            employees = new ArrayList<>();
        }
        return employees;
    }
}