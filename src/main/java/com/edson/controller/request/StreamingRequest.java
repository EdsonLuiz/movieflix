package com.edson.controller.request;

import com.edson.entity.Streaming;
import lombok.Builder;

@Builder
public record StreamingRequest(String name) {
    public static Streaming toEntity(StreamingRequest request) {
        return Streaming.builder()
                .name(request.name())
                .build();
    }
}
