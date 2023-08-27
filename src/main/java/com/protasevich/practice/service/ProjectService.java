package com.protasevich.practice.service;

import com.protasevich.practice.entity.ProjectEntity;
import com.protasevich.practice.exception.ObjectNotFound;
import com.protasevich.practice.exception.ParametersNotSpecified;
import com.protasevich.practice.model.ProjectModel;
import com.protasevich.practice.repository.EmployeeRepo;
import com.protasevich.practice.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

    public void save(ProjectModel model)
            throws ParametersNotSpecified {
        if (model.getName() == null) throw new ParametersNotSpecified();
        projectRepo.save(ProjectModel.toEntity(model));
    }

    public ProjectModel get(Long id) throws ObjectNotFound {
        return ProjectModel.toModel(projectRepo.findById(id)
                .orElseThrow(ObjectNotFound::new));
    }

    public List<ProjectModel> getAll(PageRequest pageRequest) {
        Page<ProjectEntity> page = projectRepo.findAll(pageRequest);
        return ProjectModel.toListModel(page.getContent());
    }

    public void delete(Long id) throws ObjectNotFound {
        projectRepo.delete(projectRepo.findById(id).orElseThrow(ObjectNotFound::new));
    }
}
