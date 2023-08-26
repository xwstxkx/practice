package com.protasevich.practice.controller;

import com.protasevich.practice.exception.ObjectNotFound;
import com.protasevich.practice.exception.ParametersNotSpecified;
import com.protasevich.practice.model.ProjectModel;
import com.protasevich.practice.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @PostMapping("/save")
    @Operation(summary = "Сохранение одного проекта")
    public ResponseEntity save(@RequestBody ProjectModel model)
            throws ParametersNotSpecified {
        service.save(model);
        return ResponseEntity.ok("Отдел был успешно сохранён!");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение отдного проекта")
    public ResponseEntity get(@PathVariable Long id) throws ObjectNotFound {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Получение списка всех проектов")
    public ResponseEntity getAll(@RequestParam(required = false, defaultValue = "0") int page,
                                 @RequestParam(required = false, defaultValue = "10") int size)
            throws ParametersNotSpecified {
        if (page < 0 || size < 1) {
            throw new ParametersNotSpecified();
        }
        return ResponseEntity.ok(service.getAll(PageRequest.of(page, size)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление проекта")
    public ResponseEntity delete(@PathVariable Long id) throws ObjectNotFound {
        service.delete(id);
        return ResponseEntity.ok("Проект был успешно удалён");
    }

    @PutMapping("/put")
    @Operation(summary = "Обновление информации по проекту")
    public ResponseEntity put(@RequestBody ProjectModel model)
            throws ParametersNotSpecified {
        service.save(model);
        return ResponseEntity.ok("Отдел был успешно сохранён!");
    }
}
