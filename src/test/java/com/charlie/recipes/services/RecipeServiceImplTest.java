package com.charlie.recipes.services;

import com.charlie.recipes.domain.Recipe;
import com.charlie.recipes.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipesTest() {

        Recipe recipe = new Recipe();
        HashSet<Recipe> recipesValue = new HashSet<>();
        recipesValue.add(recipe);


        when(recipeService.getRecipes()).thenReturn(recipesValue);

        Set<Recipe> recipes = recipeService.getRecipes();

        // Number of recipes returned should be one
        assertEquals("Recipes collection size should be 1",1, recipes.size());

        // find all should only be run once for this interaction.
        verify(recipeRepository,times(1)).findAll();
    }

    @Test
    public void getRecipeByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.getRecipeById(1L);

        assertNotNull("Null Recipe Returned",recipeReturned);

        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,times(0)).findAll();
    }
}