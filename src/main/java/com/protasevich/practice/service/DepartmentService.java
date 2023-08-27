package com.protasevich.practice.service;

import com.protasevich.practice.entity.DepartmentEntity;
import com.protasevich.practice.exception.ObjectNotFound;
import com.protasevich.practice.exception.ParametersNotSpecified;
import com.protasevich.practice.model.DepartmentModel;
import com.protasevich.practice.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    public void saveDepartment(DepartmentModel departmentModel)
            throws ParametersNotSpecified {
        if (departmentModel.getName() == null) throw new ParametersNotSpecified();
        departmentRepo.save(DepartmentModel.toEntity(departmentModel));
    }

    public DepartmentModel get(Long id) throws ObjectNotFound {
        return DepartmentModel.toModel(departmentRepo.findById(id)
                .orElseThrow(ObjectNotFound::new));
    }

    public List<DepartmentModel> getAll(PageRequest pageRequest) {
        Page<DepartmentEntity> page = departmentRepo.findAll(pageRequest);
        return DepartmentModel.toListModel(page.getContent());
    }

    public void deleteDepartment(Long id) throws ObjectNotFound {
        departmentRepo.delete(departmentRepo.findById(id).orElseThrow(ObjectNotFound::new));
    }
}
