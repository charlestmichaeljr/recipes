package com.charlie.recipes.converters;

import com.charlie.recipes.commands.CategoryCommand;
import com.charlie.recipes.commands.IngredientCommand;
import com.charlie.recipes.commands.RecipeCommand;
import com.charlie.recipes.domain.Category;
import com.charlie.recipes.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand,Recipe> {

    private final NotesCommandToNotes notesCommandToNotes;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final CategoryCommandToCategory categoryCommandToCategory;

    public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 CategoryCommandToCategory categoryCommandToCategory) {
        this.notesCommandToNotes = notesCommandToNotes;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.categoryCommandToCategory = categoryCommandToCategory;
    }

    @Override
    public Recipe convert(RecipeCommand source) {
        if(source == null) {
            return null;
        }

        Recipe recipe = new Recipe();

        recipe.setUrl(source.getUrl());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setId(source.getId());
        recipe.setNotes(notesCommandToNotes.convert(source.getNotes()));
        recipe.setDescription(source.getDescription());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());

        recipe.setId(source.getId());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDirections(source.getDirections());

        if(source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories().forEach((CategoryCommand categoryCommand)-> recipe.getCategories()
                    .add(categoryCommandToCategory.convert(categoryCommand)));
        }

        if(source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients().forEach((IngredientCommand ingredientCommand)->recipe.getIngredients()
                    .add(ingredientCommandToIngredient.convert(ingredientCommand)));
        }

        return recipe;

    }
}
