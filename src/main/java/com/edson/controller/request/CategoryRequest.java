package com.edson.controller.request;

import com.edson.entity.Category;

public record CategoryRequest(String name) {
    public static Category toEntity(CategoryRequest request) {
        return Category.builder()
                .name(request.name)
                .build();
    }

}
