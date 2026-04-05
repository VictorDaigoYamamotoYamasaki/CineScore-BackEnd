package com.cinescore.controller;

import com.cinescore.dto.ReviewRequestDTO;
import com.cinescore.dto.ReviewResponseDTO;
import com.cinescore.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // GET /api/reviews/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.buscarPorId(id));
    }

    // GET /api/reviews/movie/{imdbId}
    @GetMapping("/movie/{imdbId}")
    public ResponseEntity<List<ReviewResponseDTO>> listarPorFilme(@PathVariable String imdbId) {
        return ResponseEntity.ok(reviewService.listarPorFilme(imdbId));
    }

    // GET /api/reviews/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewResponseDTO>> listarPorUsuario(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.listarPorUsuario(userId));
    }

    // POST /api/reviews?userId={id}
    @PostMapping
    public ResponseEntity<ReviewResponseDTO> criar(
            @RequestParam Long userId,
            @Valid @RequestBody ReviewRequestDTO dto) {
        return ResponseEntity.status(201).body(reviewService.criar(userId, dto));
    }

    // PUT /api/reviews/{id}?userId={id}
    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestParam Long userId,
            @Valid @RequestBody ReviewRequestDTO dto) {
        return ResponseEntity.ok(reviewService.atualizar(id, userId, dto));
    }

    // DELETE /api/reviews/{id}?userId={id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id,
            @RequestParam Long userId) {
        reviewService.deletar(id, userId);
        return ResponseEntity.noContent().build();
    }
}
