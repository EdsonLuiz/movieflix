package com.edson.controller.response;

import com.edson.entity.Streaming;
import lombok.Builder;

@Builder
public record StreamingResponse(Long id, String name) {
    public static StreamingResponse fromEntity(Streaming entity) {
        return StreamingResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
