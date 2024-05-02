package com.example.testtaskpumb.service;

import com.example.testtaskpumb.entity.Animal;
import com.example.testtaskpumb.entity.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AnimalService {
    void loadFiles(MultipartFile... files);
    List<Animal> getFilteredAnimals(String type, Category category, String sex, String sortBy, String sortDirection);

}
