package com.protasevich.practice.model;

import com.ibm.icu.text.Transliterator;
import com.protasevich.practice.entity.EmployeeEntity;
import lombok.Data;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class EmployeeModel {

    private Long id;
    private String name;
    private String surname;
    private String date;
    private String workBegin;
    private String skill;
    private Long departmentId;
    private Long projectId;
    private String email;
    private String password;
    private Integer salary;
    private Double cleanSalary;

    public static EmployeeModel toModel(EmployeeEntity entity) {
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setId(entity.getId());
        employeeModel.setName(entity.getName());
        employeeModel.setSurname(entity.getSurname());
        employeeModel.setDate(entity.getDate());
        employeeModel.setWorkBegin(entity.getWorkBegin());
        employeeModel.setEmail(entity.getEmail());
        if (entity.getPassword() == null){
            employeeModel.setPassword(generateRandomPassword(12));
        } else employeeModel.setPassword(entity.getPassword());
        employeeModel.setSkill(entity.getSkill());
        employeeModel.setSalary(entity.getSalary());
        employeeModel.setCleanSalary(entity.getCleanSalary());
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
        employeeEntity.setWorkBegin(model.getWorkBegin());
        employeeEntity.setEmail(EmployeeModel.toEmail(model));
        if (model.getPassword() == null){
            employeeEntity.setPassword(generateRandomPassword(12));
        } else employeeEntity.setPassword(model.getPassword());
        employeeEntity.setSkill(model.getSkill());
        if (model.getSalary() == null){
            employeeEntity.setSalary(EmployeeModel.toSalary(model));
        } else employeeEntity.setSalary(model.getSalary());
        employeeEntity.setCleanSalary(EmployeeModel.toCleanSalary(model));
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
        String CYRILLIC_TO_LATIN = "Russian-Latin/BGN";
        int random = (int) (Math.random()*(600+1));
        Transliterator toLatinTrans = Transliterator.getInstance(CYRILLIC_TO_LATIN);
        String name = toLatinTrans.transliterate(model.getName().toLowerCase());
        String surname = toLatinTrans.transliterate(model.getSurname().toLowerCase());
        return name + "." + surname + random + "@gmail.com" ;
    }

    public static Integer toSalary(EmployeeModel model) {
        int skillEmp;
        switch (model.getSkill()) {
            case ("Junior") -> skillEmp = 700;
            case ("Junior+") -> skillEmp = 1000;
            case ("Middle") -> skillEmp = 1500;
            case ("Middle+") -> skillEmp = 2000;
            case ("Senior") -> skillEmp = 3000;
            default -> skillEmp = 300;
        }
        return skillEmp;
    }

    public static Double toCleanSalary(EmployeeModel model) {
        Integer salaryEmp;
        if (model.getSalary() == null){
            salaryEmp= EmployeeModel.toSalary(model);
        } else salaryEmp=model.getSalary();
        return salaryEmp * 0.86;
    }

    public static String generateRandomPassword(int len)
    {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }
}
