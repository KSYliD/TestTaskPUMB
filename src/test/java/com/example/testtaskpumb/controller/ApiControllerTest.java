package com.example.testtaskpumb.controller;

import com.example.testtaskpumb.entity.Animal;
import com.example.testtaskpumb.entity.Category;
import com.example.testtaskpumb.service.AnimalService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ApiControllerTest {

    @Mock
    private AnimalService animalService;

    @InjectMocks
    private ApiController apiController;

    private final MockMvc mockMvc;

    public ApiControllerTest() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
    }

    @Test
    public void testGetFilteredAnimals() throws Exception {
        Animal animal1 = new Animal("Dog", "Pet", "Male", 12, 25);
        Animal animal2 = new Animal("Dog", "Pet", "Male", 15, 30);
        when(animalService.getFilteredAnimals(anyString(), any(Category.class), anyString(), anyString(), anyString()))
                .thenReturn(List.of(animal1, animal2));

        mockMvc.perform(get("/api/animals")
                        .param("type", "dog")
                        .param("category", "FIRST_CATEGORY")
                        .param("sex", "male")
                        .param("sortBy", "name")
                        .param("sortDirection", "asc"))
                .andExpect(status().isOk());

        verify(animalService).getFilteredAnimals("dog", Category.FIRST_CATEGORY, "male", "name", "asc");
    }

    @Test
    public void testLoadFiles() throws Exception {
        MockMultipartFile file1 = new MockMultipartFile("file", "test.xml", MediaType.TEXT_XML_VALUE, "test file".getBytes());
        MockMultipartFile file2 = new MockMultipartFile("file", "test2.csv", "text/csv" , "test file 2".getBytes());
        MockMultipartFile[] files = {file1, file2};

        doNothing().when(animalService).loadFiles(any(MultipartFile[].class));

        mockMvc.perform(
                post("/files/uploads").contentType(MediaType.MULTIPART_FORM_DATA)
        ).andExpect(status().isOk());

        apiController.loadFiles(files);
        verify(animalService, times(1)).loadFiles(files);

    }


}
