package com.edson.controller;

import com.edson.controller.request.StreamingRequest;
import com.edson.controller.response.StreamingResponse;
import com.edson.entity.Streaming;
import com.edson.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/streaming")
@RequiredArgsConstructor
public class StreamingController {
    private final StreamingService service;

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAll() {
        var response = service.findAll().stream()
                .map(StreamingResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(streaming -> ResponseEntity.ok(StreamingResponse.fromEntity(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> save(@RequestBody StreamingRequest request) {
        Streaming streaming = service.save(StreamingRequest.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingResponse.fromEntity(streaming));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
