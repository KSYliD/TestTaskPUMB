package com.example.testtaskpumb.controller;

import com.example.testtaskpumb.entity.Animal;
import com.example.testtaskpumb.entity.Category;
import com.example.testtaskpumb.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@AllArgsConstructor
public class ApiController {

    private AnimalService animalService;

    @GetMapping("/api/animals")
    public List<Animal> getFilteredAnimals(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) String sex,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDirection) {

        return animalService.getFilteredAnimals(type, category, sex, sortBy, sortDirection);
    }


    @PostMapping("/files/uploads")
    public void loadFiles(@RequestBody MultipartFile... files) {
        animalService.loadFiles(files);
    }
}
