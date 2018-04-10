package com.charlie.recipes.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setup() {
        category = new Category();
    }

    @Test
    public void getId() {
        Long idValue = 4L;
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {
        String descriptionValue = "guacamole";
        category.setDescription(descriptionValue);
        assertEquals(descriptionValue, category.getDescription());
    }

    @Test
    public void getRecipes() {
        /*
        HashSet<Recipe> recipesValue = new HashSet<>().add(new Recipe());
        */
    }
}