package com.example.plant_journal.controller;

import com.example.plant_journal.model.Plant;
import com.example.plant_journal.service.PlantService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ViewController {

    private final PlantService plantService;

    public ViewController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping("/")
    public String index(Model model) {
        // 植物一覧を取得してモデルに追加
        List<Plant> plants = plantService.getAllPlants();
        model.addAttribute("plants", plants);
        // フォーム用の空の Plant オブジェクトを追加
        model.addAttribute("plant", new Plant());
        return "index"; // templates/index.html を返す
    }

    @PostMapping("/")
    public String addPlant(@Valid Plant plant, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // バリデーションエラーがあれば、一覧も再表示
            model.addAttribute("plants", plantService.getAllPlants());
            return "index";
        }
        plantService.addPlant(plant);
        return "redirect:/";
    }

    // ★ 削除処理用のエンドポイントを追加 ★
    @PostMapping("/delete")
    public String deletePlant(@RequestParam Long id) {
        plantService.deletePlant(id);
        return "redirect:/";
    }
}
