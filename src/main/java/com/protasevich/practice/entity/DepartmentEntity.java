package com.protasevich.practice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Пожалуйста, укажите название отдела")
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "department")
    private List<EmployeeEntity> employees;

    public List<EmployeeEntity> getEmployees() {
        if (employees == null) {
            employees = new ArrayList<>();
        }
        return employees;
    }
}
