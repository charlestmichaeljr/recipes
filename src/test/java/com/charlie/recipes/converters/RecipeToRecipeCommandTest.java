package com.charlie.recipes.converters;

import com.charlie.recipes.commands.CategoryCommand;
import com.charlie.recipes.commands.IngredientCommand;
import com.charlie.recipes.commands.NotesCommand;
import com.charlie.recipes.commands.RecipeCommand;
import com.charlie.recipes.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RecipeToRecipeCommandTest {

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


    RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(),new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),new NotesToNotesCommand());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() {

        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setUrl(URL);
        recipe.setSource(SOURCE);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        recipe.setNotes(notes);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGRED_ID_1);
        recipe.getIngredients().add(ingredient1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGRED_ID_2);
        recipe.getIngredients().add(ingredient2);


        Category category1 = new Category();
        category1.setId(CAT_ID_1);
        recipe.getCategories().add(category1);

        Category category2 = new Category();
        category1.setId(CAT_ID_2);
        recipe.getCategories().add(category2);


        RecipeCommand command = converter.convert(recipe);

        assertNotNull(command);
        assertEquals(RECIPE_ID,command.getId());
        assertEquals(DESCRIPTION,command.getDescription());
        assertEquals(PREP_TIME,command.getPrepTime());
        assertEquals(COOK_TIME,command.getCookTime());
        assertEquals(SERVINGS,command.getServings());
        assertEquals(SOURCE,command.getSource());
        assertEquals(URL,command.getUrl());
        assertEquals(DIRECTIONS,command.getDirections());
        assertEquals(DIFFICULTY,command.getDifficulty());

        assertNotNull(command.getNotes());
        assertEquals(NOTES_ID,command.getNotes().getId());

        assertNotNull(command.getIngredients());
        assertEquals(2,command.getIngredients().size());

        assertNotNull(command.getCategories());
        assertEquals(2,command.getCategories().size());


    }
}
