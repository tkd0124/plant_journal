package com.example.plant_journal.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private LocalDate dateAdded;
    private String notes;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
    private List<GrowthLog> growthLogs;

    // コンストラクタ（デフォルト）
    public Plant() {}

    // ゲッター・セッター
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public LocalDate getDateAdded() { return dateAdded; }
    public void setDateAdded(LocalDate dateAdded) { this.dateAdded = dateAdded; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public List<GrowthLog> getGrowthLogs() { return growthLogs; }
    public void setGrowthLogs(List<GrowthLog> growthLogs) { this.growthLogs = growthLogs; }
}

