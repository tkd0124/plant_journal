package com.example.plant_journal.service;

import com.example.plant_journal.model.Plant;
import com.example.plant_journal.repository.PlantRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    // 🌿 植物を登録する
    public Plant addPlant(Plant plant) {
        return plantRepository.save(plant);
    }

    // 🌿 植物の一覧を取得する
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    // 🌿 特定の植物を取得する（見つからない場合 404）
    public Plant getPlantById(Long id) {
        return plantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID " + id + " の植物が見つかりません！"));
    }

    // 🌿 植物の情報を更新する
    public Plant updatePlant(Long id, Plant updatedPlant) {
        Plant plant = plantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID " + id + " の植物が見つかりません！"));

        plant.setName(updatedPlant.getName());
        plant.setType(updatedPlant.getType());
        plant.setDateAdded(updatedPlant.getDateAdded());
        plant.setNotes(updatedPlant.getNotes());

        return plantRepository.save(plant);
    }

    // 🌿 植物を削除する
    public void deletePlant(Long id) {
        if (!plantRepository.existsById(id)) {
            throw new EntityNotFoundException("ID " + id + " の植物が見つかりません！");
        }
        plantRepository.deleteById(id);
    }

}
