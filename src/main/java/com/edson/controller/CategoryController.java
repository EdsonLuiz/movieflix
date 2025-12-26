package com.edson.controller;

import com.edson.controller.request.CategoryRequest;
import com.edson.controller.response.CategoryResponse;
import com.edson.entity.Category;
import com.edson.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        var response = service.findAll().stream()
                .map(CategoryResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(category -> ResponseEntity.ok(CategoryResponse.fromEntity(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> save(@RequestBody CategoryRequest request) {
        Category category = service.save(CategoryRequest.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryResponse.fromEntity(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
