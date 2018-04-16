package com.charlie.recipes.converters;

import com.charlie.recipes.commands.CategoryCommand;
import com.charlie.recipes.commands.IngredientCommand;
import com.charlie.recipes.commands.NotesCommand;
import com.charlie.recipes.commands.RecipeCommand;
import com.charlie.recipes.domain.Difficulty;
import com.charlie.recipes.domain.Ingredient;
import com.charlie.recipes.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RecipeCommandToRecipeTest {

    Long RECIPE_ID = new Long(1L);
    String DESCRIPTION = "My Description";
    private Integer PREP_TIME = 40;
    private Integer COOK_TIME = 20;
    private Integer SERVINGS = 4;
    private String SOURCE = "Recipes.com";
    private String URL = "http://www.blabla.com";
    private String DIRECTIONS = "Heat it up.It's done";
    private Difficulty DIFFICULTY = Difficulty.MODERATE;

    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID_2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;


    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new NotesCommandToNotes(),new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new CategoryCommandToCategory());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {

        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() {
        RecipeCommand command = new RecipeCommand();
        command.setId(RECIPE_ID);
        command.setDescription(DESCRIPTION);
        command.setUrl(URL);
        command.setSource(SOURCE);
        command.setPrepTime(PREP_TIME);
        command.setCookTime(COOK_TIME);
        command.setServings(SERVINGS);
        command.setDifficulty(DIFFICULTY);
        command.setDirections(DIRECTIONS);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);

        command.setNotes(notesCommand);

        IngredientCommand ingredientsCommand1 = new IngredientCommand();
        ingredientsCommand1.setId(INGRED_ID_1);
        command.getIngredients().add(ingredientsCommand1);

        IngredientCommand ingredientsCommand2 = new IngredientCommand();
        ingredientsCommand2.setId(INGRED_ID_2);
        command.getIngredients().add(ingredientsCommand2);

        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CAT_ID_1);
        command.getCategories().add(categoryCommand1);

        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(CAT_ID_2);
        command.getCategories().add(categoryCommand2);

        Recipe recipe = converter.convert(command);

        assertNotNull(recipe);
        assertEquals(RECIPE_ID,recipe.getId());
        assertEquals(DESCRIPTION,recipe.getDescription());
        assertEquals(PREP_TIME,recipe.getPrepTime());
        assertEquals(COOK_TIME,recipe.getCookTime());
        assertEquals(SERVINGS,recipe.getServings());
        assertEquals(SOURCE,recipe.getSource());
        assertEquals(URL,recipe.getUrl());
        assertEquals(DIRECTIONS,recipe.getDirections());
        assertEquals(DIFFICULTY,recipe.getDifficulty());

        assertNotNull(recipe.getNotes());
        assertEquals(NOTES_ID,recipe.getNotes().getId());

        assertNotNull(recipe.getIngredients());
        assertEquals(2,recipe.getIngredients().size());

        assertNotNull(recipe.getCategories());
        assertEquals(2,recipe.getCategories().size());
    }
}
