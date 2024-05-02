package com.example.testtaskpumb.repository;

import com.example.testtaskpumb.entity.Animal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimalRepositoryTest {

    @Mock
    private AnimalRepository animalRepository;

    @Test
    void testSaveAnimal() {
        Animal animalToSave = new Animal("Dog", "Pet", "Male", 12, 25);
        Animal savedAnimal = new Animal("Dog", "Pet", "Male", 15, 30);
        savedAnimal.setId(1L);

        when(animalRepository.save(animalToSave)).thenReturn(savedAnimal);

        Animal result = animalRepository.save(animalToSave);

        assertEquals(savedAnimal, result);
        verify(animalRepository, times(1)).save(animalToSave);
    }

    @Test
    void testFindAllAnimals() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Dog", "Pet", "Male", 10, 30));
        animals.add(new Animal("Cat", "Pet", "Female", 8, 25));

        when(animalRepository.findAll(any(Sort.class))).thenReturn(animals);

        List<Animal> result = animalRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

        assertEquals(animals, result);
        verify(animalRepository, times(1)).findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
}
