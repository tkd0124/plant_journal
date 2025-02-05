//package com.example.plant_journal.repository;
//
//public class PlantRepository {
//}
package com.example.plant_journal.repository;

import com.example.plant_journal.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {
}
