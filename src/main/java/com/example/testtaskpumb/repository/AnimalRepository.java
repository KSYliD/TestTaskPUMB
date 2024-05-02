package com.example.testtaskpumb.repository;

import com.example.testtaskpumb.entity.Animal;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Animal save(Animal animal);
    List<Animal> findAll(Specification<Animal> spec, Sort sort);
}
