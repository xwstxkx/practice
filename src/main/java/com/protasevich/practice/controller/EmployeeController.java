package com.protasevich.practice.controller;

import com.protasevich.practice.exception.ObjectNotFound;
import com.protasevich.practice.exception.ParametersNotSpecified;
import com.protasevich.practice.model.EmployeeModel;
import com.protasevich.practice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/save")
    @Operation(summary = "Регистрация одного работника")
    public ResponseEntity save(@RequestBody EmployeeModel model,
                               @RequestParam Long departmentId,
                               @RequestParam Long projectId)
            throws ParametersNotSpecified, ObjectNotFound {
        service.save(model, departmentId, projectId);
        return ResponseEntity.ok("Работник был успешно сохранён!");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение отдного работника")
    public ResponseEntity get(@PathVariable Long id) throws ObjectNotFound {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Получение списка всех работников")
    public ResponseEntity getAll(@RequestParam(required = false, defaultValue = "0") int page,
                                 @RequestParam(required = false, defaultValue = "10") int size)
            throws ParametersNotSpecified {
        if (page < 0 || size < 1) {
            throw new ParametersNotSpecified();
        }
        return ResponseEntity.ok(service.getAll(PageRequest.of(page, size)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление работника")
    public ResponseEntity delete(@PathVariable Long id) throws ObjectNotFound {
        service.delete(id);
        return ResponseEntity.ok("Работник был успешно удалён");
    }

    @PutMapping("/put")
    @Operation(summary = "Обновление информации по работнику")
    public ResponseEntity put(@RequestBody EmployeeModel model,
                              @RequestParam Long departmentId,
                              @RequestParam Long projectId)
            throws ParametersNotSpecified, ObjectNotFound {
        service.save(model, departmentId, projectId);
        return ResponseEntity.ok("Работник был успешно обновлён!");
    }
}
