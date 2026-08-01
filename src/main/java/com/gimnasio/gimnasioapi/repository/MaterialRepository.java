package com.gimnasio.gimnasioapi.repository;

import com.gimnasio.gimnasioapi.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}