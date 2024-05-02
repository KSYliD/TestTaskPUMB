package com.example.testtaskpumb.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryTest {

    @Test
    void testFirstCategory() {
        Category category = Category.FIRST_CATEGORY;
        assertNotNull(category);
        assertEquals("FIRST_CATEGORY", category.name());
    }

    @Test
    void testSecondCategory() {
        Category category = Category.SECOND_CATEGORY;
        assertNotNull(category);
        assertEquals("SECOND_CATEGORY", category.name());
    }

    @Test
    void testThirdCategory() {
        Category category = Category.THIRD_CATEGORY;
        assertNotNull(category);
        assertEquals("THIRD_CATEGORY", category.name());
    }

    @Test
    void testFourthCategory() {
        Category category = Category.FOURTH_CATEGORY;
        assertNotNull(category);
        assertEquals("FOURTH_CATEGORY", category.name());
    }
}
