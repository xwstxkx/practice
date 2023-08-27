package com.protasevich.practice.repository;

import com.protasevich.practice.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<ProjectEntity, Long> {
}
