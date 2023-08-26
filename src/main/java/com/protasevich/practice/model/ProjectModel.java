package com.protasevich.practice.model;

import com.protasevich.practice.entity.EmployeeEntity;
import com.protasevich.practice.entity.ProjectEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProjectModel {

    private Long id;
    private String name;
    private String Date;
    private List<EmployeeModel> employees;

    public static ProjectModel toModel(ProjectEntity entity) {
        ProjectModel model = new ProjectModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setDate(entity.getDate());
        for (EmployeeEntity employees : entity.getEmployees()) {
            model.getEmployees().add(EmployeeModel.toModel(employees));
        }
        return model;
    }

    public static List<ProjectModel> toListModel(List<ProjectEntity> entities) {
        List<ProjectModel> models = new ArrayList<>();
        for (ProjectEntity entity : entities) {
            models.add(toModel(entity));
        }
        return models;
    }

    public static ProjectEntity toEntity(ProjectModel model) {
        ProjectEntity entity = new ProjectEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setDate(model.getDate());
        return entity;
    }

    public static List<ProjectEntity> toListEntity(List<ProjectModel> models) {
        List<ProjectEntity> employeeModelList = new ArrayList<>();
        for (ProjectModel model : models) {
            employeeModelList.add(toEntity(model));
        }
        return employeeModelList;
    }

    public List<EmployeeModel> getEmployees() {
        if (employees == null) {
            employees = new ArrayList<>();
        }
        return employees;
    }
}
