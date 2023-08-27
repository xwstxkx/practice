package com.protasevich.practice.controller;

import com.protasevich.practice.exception.ObjectNotFound;
import com.protasevich.practice.exception.ParametersNotSpecified;
import com.protasevich.practice.model.DepartmentModel;
import com.protasevich.practice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/save")
    @Operation(summary = "Сохранение одного отдела")
    public ResponseEntity save(@RequestBody DepartmentModel model)
            throws ParametersNotSpecified {
        departmentService.saveDepartment(model);
        return ResponseEntity.ok("Отдел был успешно сохранён!");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение отдного отдела")
    public ResponseEntity get(@PathVariable Long id) throws ObjectNotFound {
        return ResponseEntity.ok(departmentService.get(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Получение списка всех отделов")
    public ResponseEntity getAll(@RequestParam(required = false, defaultValue = "0") int page,
                                 @RequestParam(required = false, defaultValue = "10") int size)
            throws ParametersNotSpecified {
        if (page < 0 || size < 1) {
            throw new ParametersNotSpecified();
        }
        return ResponseEntity.ok(departmentService.getAll(PageRequest.of(page, size)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление отдела")
    public ResponseEntity delete(@PathVariable Long id) throws ObjectNotFound {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("Отдел был успешно удалён");
    }

    @PutMapping("/put")
    @Operation(summary = "Обновление информации по отделу")
    public ResponseEntity put(@RequestBody DepartmentModel departmentModel)
            throws ParametersNotSpecified {
        departmentService.saveDepartment(departmentModel);
        return ResponseEntity.ok("Отдел был успешно сохранён!");
    }
}
