package com.protasevich.practice.service;

import com.protasevich.practice.entity.EmployeeEntity;
import com.protasevich.practice.exception.ObjectNotFound;
import com.protasevich.practice.exception.ParametersNotSpecified;
import com.protasevich.practice.model.EmployeeModel;
import com.protasevich.practice.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public void save(EmployeeModel model)
            throws ParametersNotSpecified {
        if (model.getName() == null) throw new ParametersNotSpecified();
        employeeRepo.save(EmployeeModel.toEntity(model));
    }

    public EmployeeModel get(Long id) throws ObjectNotFound {
        return EmployeeModel.toModel(employeeRepo.findById(id)
                .orElseThrow(ObjectNotFound::new));
    }

    public List<EmployeeModel> getAll(PageRequest pageRequest) {
        Page<EmployeeEntity> page = employeeRepo.findAll(pageRequest);
        return EmployeeModel.toListModel(page.getContent());
    }

    public void delete(Long id) throws ObjectNotFound {
        employeeRepo.delete(employeeRepo.findById(id).orElseThrow(ObjectNotFound::new));
    }
}
