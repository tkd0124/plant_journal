package com.example.plant_journal.controller;

import com.example.plant_journal.model.GrowthLog;
import com.example.plant_journal.repository.GrowthLogRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/growth-logs")
public class GrowthLogController {

    private final GrowthLogRepository growthLogRepository;

    public GrowthLogController(GrowthLogRepository growthLogRepository) {
        this.growthLogRepository = growthLogRepository;
    }

    // 🌱 成長記録の一覧を取得（GET /growth-logs）
    @GetMapping
    public List<GrowthLog> getAllGrowthLogs() {
        return growthLogRepository.findAll();
    }

    // 🌱 成長記録を登録（POST /growth-logs）
    @PostMapping
    public ResponseEntity<GrowthLog> createGrowthLog(@RequestBody GrowthLog growthLog) {
        GrowthLog savedLog = growthLogRepository.save(growthLog);
        return ResponseEntity.ok(savedLog);
    }

    // 🌱 成長記録を削除（DELETE /growth-logs/{id}）
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrowthLog(@PathVariable Long id) {
        if (growthLogRepository.existsById(id)) {
            growthLogRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
