package com.example.plant_journal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ★ バリデーション追加：名前は空文字やnullを許可しない
    @NotBlank(message = "名前は必須です")
    @Size(min = 2, max = 50, message = "名前は2文字以上50文字以下で入力してください")
    private String name;

    // ★ バリデーション追加：種類も空文字やnullを許可しない
    @NotBlank(message = "種類は必須です")
    @Size(min = 1, max = 50, message = "種類は1文字以上50文字以下で入力してください")
    private String type;

    private LocalDate dateAdded;
    private String notes;

    // GrowthLogとのリレーション
    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
    private List<GrowthLog> growthLogs;

    // コンストラクタ（デフォルト）
    public Plant() {}

    // DBにINSERTされる直前に現在の日付をセット
    @PrePersist
    protected void onCreate() {
        this.dateAdded = LocalDate.now();
    }

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
