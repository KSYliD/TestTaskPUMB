package com.example.testtaskpumb.service;

import com.example.testtaskpumb.entity.Animal;
import com.example.testtaskpumb.entity.Category;
import com.example.testtaskpumb.repository.AnimalRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AnimalServiceImplTest {

    @Mock
    private AnimalRepository animalRepository;

    @Test
    public void testLoadInvalidFileFormat() {
        MultipartFile invalidFile = mock(MultipartFile.class);
        when(invalidFile.getContentType()).thenReturn("text/plain");

        AnimalServiceImpl service = new AnimalServiceImpl(animalRepository);
        service.loadFiles(invalidFile);
    }
    @Test
    public void testGetFilteredAnimalsAllFilters() {
        String type = "Cat";
        Category category = Category.FIRST_CATEGORY;
        String sex = "Male";
        String sortBy = "name";
        String sortDirection = "asc";

        List<Animal> expectedAnimals = List.of(new Animal("Whiskers", type, sex, 5.0, 100.0));
        AnimalRepository mockRepository = mock(AnimalRepository.class);
        when(mockRepository.findAll(any(Specification.class), any(Sort.class))).thenReturn(expectedAnimals);

        AnimalServiceImpl service = new AnimalServiceImpl(mockRepository);

        List<Animal> actualAnimals = service.getFilteredAnimals(type, category, sex, sortBy, sortDirection);

        assertEquals(expectedAnimals, actualAnimals);
    }
}
