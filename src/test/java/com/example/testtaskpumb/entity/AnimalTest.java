package com.example.testtaskpumb.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    private Animal animal;

    @BeforeEach
    void setUp() {
        animal = new Animal("Dog", "Pet", "Male", 10, 30);
    }

    @Test
    void testAnimalCreation() {
        assertNotNull(animal);
        assertEquals("Dog", animal.getName());
        assertEquals("Pet", animal.getType());
        assertEquals("Male", animal.getSex());
        assertEquals(10, animal.getWeight());
        assertEquals(30, animal.getCost());
        assertEquals(Category.SECOND_CATEGORY, animal.getCategory());
    }

    @Test
    void testAnimalWithNegativeWeight() {
        animal = new Animal("Dog", "Pet", "Male", -10, 30);
        assertEquals(0, animal.getWeight());
    }

    @Test
    void testAnimalWithNegativeCost() {
        animal = new Animal("Dog", "Pet", "Male", 10, -30);
        assertEquals(0, animal.getCost());
    }

    @Test
    void testAnimalWithCostInRange() {
        animal = new Animal("Dog", "Pet", "Male", 10, 20);
        assertEquals(Category.FIRST_CATEGORY, animal.getCategory());
    }

}