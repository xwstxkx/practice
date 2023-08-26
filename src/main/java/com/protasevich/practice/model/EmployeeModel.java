package com.protasevich.practice.model;

import com.ibm.icu.text.Transliterator;
import com.protasevich.practice.entity.EmployeeEntity;
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
        String st = "привет мир";
        String CYRILLIC_TO_LATIN = "Russian-Latin/BGN";
        Transliterator toLatinTrans = Transliterator.getInstance(CYRILLIC_TO_LATIN);
        String name = toLatinTrans.transliterate(model.getName().toLowerCase());
        String surname = toLatinTrans.transliterate(model.getSurname().toLowerCase());
        return name + "." + surname + "@gmail.com";
    }
}
