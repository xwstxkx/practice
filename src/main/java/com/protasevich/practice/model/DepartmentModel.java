package com.protasevich.practice.model;

import com.protasevich.practice.entity.DepartmentEntity;
import com.protasevich.practice.entity.EmployeeEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DepartmentModel {

    private Long id;
    private String name;
    private List<EmployeeModel> employees;

    public static DepartmentModel toModel(DepartmentEntity entity) {
        DepartmentModel departmentModel = new DepartmentModel();
        departmentModel.setId(entity.getId());
        departmentModel.setName(entity.getName());
        for (EmployeeEntity employees : entity.getEmployees()) {
            departmentModel.getEmployees().add(EmployeeModel.toModel(employees));
        }
        return departmentModel;
    }
    public static List<DepartmentModel> toListModel(List<DepartmentEntity> departmentEntities) {
        List<DepartmentModel> employeeModelList = new ArrayList<>();
        for (DepartmentEntity departmentEntity : departmentEntities) {
            employeeModelList.add(toModel(departmentEntity));
        }
        return employeeModelList;
    }

    public static DepartmentEntity toEntity(DepartmentModel model) {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(model.getId());
        departmentEntity.setName(model.getName());
        return departmentEntity;
    }
    public static List<DepartmentEntity> toListEntity(List<DepartmentModel> departmentModels) {
        List<DepartmentEntity> employeeModelList = new ArrayList<>();
        for (DepartmentModel departmentModel : departmentModels) {
            employeeModelList.add(toEntity(departmentModel));
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
