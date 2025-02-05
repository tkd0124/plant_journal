//package com.example.plant_journal.controller;
//
//public class PlantController {
//}
package com.example.plant_journal.controller;

import com.example.plant_journal.model.Plant;
import com.example.plant_journal.repository.PlantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plants")
public class PlantController {
    private final PlantRepository plantRepository;

    public PlantController(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    // 🌿 植物を登録する
    @PostMapping
    public ResponseEntity<Plant> addPlant(@RequestBody Plant plant) {
        Plant savedPlant = plantRepository.save(plant);
        return ResponseEntity.ok(savedPlant);
    }

    // 🌿 植物の一覧を取得する
    @GetMapping
    public ResponseEntity<List<Plant>> getAllPlants() {
        List<Plant> plants = plantRepository.findAll();
        return ResponseEntity.ok(plants);
    }

    // 🌿 特定の植物を取得する
    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable Long id) {
        Optional<Plant> plant = plantRepository.findById(id);
        return plant.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🌿 植物の情報を更新する
    @PutMapping("/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable Long id, @RequestBody Plant updatedPlant) {
        return plantRepository.findById(id)
                .map(plant -> {
                    plant.setName(updatedPlant.getName());
                    plant.setType(updatedPlant.getType());
                    plant.setDateAdded(updatedPlant.getDateAdded());
                    plant.setNotes(updatedPlant.getNotes());
                    plantRepository.save(plant);
                    return ResponseEntity.ok(plant);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🌿 植物を削除する
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable Long id) {
        if (plantRepository.existsById(id)) {
            plantRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
