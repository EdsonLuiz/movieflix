package com.edson.controller.response;

import com.edson.entity.Category;
import lombok.Builder;

@Builder
public record CategoryResponse(Long id, String name) {
    public static CategoryResponse fromEntity(Category entity) {
        return CategoryResponse
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
