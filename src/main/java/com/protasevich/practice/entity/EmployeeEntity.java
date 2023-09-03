package com.protasevich.practice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "employees")
@Data
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "surname")
    @NotBlank
    private String surname;

    @Column(name = "date")
    @DateTimeFormat(fallbackPatterns = "dd.MM.yyyy")
    @NotBlank
    private String date;

    @Column(name = "workBegin")
    @DateTimeFormat(fallbackPatterns = "dd.MM.yyyy")
    @NotBlank
    private String workBegin;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "password")
    @NotBlank
    private String password;

    @Column(name = "skill")
    @NotBlank
    private String skill;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "clean_salary")
    private Double cleanSalary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;
}
