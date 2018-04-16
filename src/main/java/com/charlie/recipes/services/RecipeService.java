package com.charlie.recipes.services;

import com.charlie.recipes.commands.RecipeCommand;
import com.charlie.recipes.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe getRecipeById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
