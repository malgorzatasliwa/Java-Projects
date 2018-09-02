//Created by Malgorzata Sliwa 2018

package com.agh.project.repository;

import com.agh.project.model.Treasure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreasureRepository extends JpaRepository<Treasure, Long> {
}
