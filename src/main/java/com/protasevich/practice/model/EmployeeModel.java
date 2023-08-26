package com.protasevich.practice.model;

import com.protasevich.practice.entity.DepartmentEntity;
import com.protasevich.practice.entity.EmployeeEntity;
import com.protasevich.practice.entity.ProjectEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeModel {

    private Long id;
    private String name;
    private String surname;
    private String date;
    private Long departmentId;
    private Long projectId;
    private String email;
    private DepartmentEntity department;
    private ProjectEntity project;

    public static EmployeeModel toModel(EmployeeEntity entity) {
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setId(entity.getId());
        employeeModel.setName(entity.getName());
        employeeModel.setSurname(entity.getSurname());
        employeeModel.setDate(entity.getDate());
        employeeModel.setEmail(entity.getEmail());
        employeeModel.setDepartmentId(entity.getDepartment().getId());
        employeeModel.setProjectId(entity.getProject().getId());
        return employeeModel;
    }
    public static List<EmployeeModel> toListModel(List<EmployeeEntity> employeeEntities) {
        List<EmployeeModel> employeeModelList = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeEntities) {
            employeeModelList.add(toModel(employeeEntity));
        }
        return employeeModelList;
    }

    public static EmployeeEntity toEntity(EmployeeModel model) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(model.getId());
        employeeEntity.setName(model.getName());
        employeeEntity.setSurname(model.getSurname());
        employeeEntity.setDate(model.getDate());
        employeeEntity.setEmail(EmployeeModel.toEmail(model));
        employeeEntity.setDepartment(model.getDepartment());
        employeeEntity.setProject(model.getProject());
        return employeeEntity;
    }
    public static List<EmployeeEntity> toListEntity(List<EmployeeModel> employeeModels) {
        List<EmployeeEntity> employeeModelList = new ArrayList<>();
        for (EmployeeModel employeeModel : employeeModels) {
            employeeModelList.add(toEntity(employeeModel));
        }
        return employeeModelList;
    }

    public static String toEmail(EmployeeModel model) {
        return model.getName().toLowerCase() + "." + model.getSurname().toLowerCase() + "@gmail.com";
    }
}
