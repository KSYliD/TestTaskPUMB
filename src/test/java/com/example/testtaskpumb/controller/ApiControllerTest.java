package com.example.testtaskpumb.controller;


import com.example.testtaskpumb.entity.Animal;
import com.example.testtaskpumb.service.AnimalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ApiControllerTest {

    @Mock
    private AnimalService animalService;

    @InjectMocks
    private ApiController apiController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFilteredAnimals() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Dog", "Pet", "Male", 10, 30));
        animals.add(new Animal("Cat", "Pet", "Female", 8, 25));
        when(animalService.getFilteredAnimals(any(), any(), any(), any(), any())).thenReturn(animals);

        List<Animal> result = apiController.getFilteredAnimals(null, null, null, "id", "asc");

        assertEquals(animals, result);
        verify(animalService, times(1)).getFilteredAnimals(null, null, null, "id", "asc");
    }

    @Test
    void testLoadFiles() {
        MockMultipartFile file1 = new MockMultipartFile("file", "test.txt", "application/xml", "test file".getBytes());
        MockMultipartFile file2 = new MockMultipartFile("file", "test2.txt", "text/csv", "test file 2".getBytes());
        MultipartFile[] files = {file1, file2};

        apiController.loadFiles(files);

        verify(animalService, times(1)).loadFiles(files);
    }
}