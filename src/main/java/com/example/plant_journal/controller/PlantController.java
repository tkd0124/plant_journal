package com.example.plant_journal.controller;

import com.example.plant_journal.model.Plant;
import com.example.plant_journal.service.PlantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/plants")
public class PlantController {
    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    // 🌿 植物を登録する
    @PostMapping
    public String addPlant(@Valid @ModelAttribute Plant plant, org.springframework.validation.BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("plants", plantService.getAllPlants());
            return "index"; // エラーがある場合、一覧画面に戻す
        }

        plantService.addPlant(plant); // データを保存
        return "redirect:/";
    }


    // 🌿 植物の一覧を取得する
    @GetMapping
    public ResponseEntity<List<Plant>> getAllPlants() {
        List<Plant> plants = plantService.getAllPlants();
        return ResponseEntity.ok(plants);
    }

    // 🌿 特定の植物を取得する
    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable Long id) {
        Plant plant = plantService.getPlantById(id);
        return ResponseEntity.ok(plant);
    }

    // 🌿 植物の情報を更新する
    @PutMapping("/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable Long id, @RequestBody Plant updatedPlant) {
        Plant plant = plantService.updatePlant(id, updatedPlant);
        return ResponseEntity.ok(plant);
    }

    // 🌿 植物を削除する
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable Long id) {
        plantService.deletePlant(id);
        return ResponseEntity.noContent().build();
    }
}
