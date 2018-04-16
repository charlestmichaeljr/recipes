package com.charlie.recipes.converters;

import com.charlie.recipes.commands.IngredientCommand;
import com.charlie.recipes.domain.Ingredient;
import com.charlie.recipes.domain.Recipe;
import com.charlie.recipes.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class IngredientToIngredientCommandTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long ID_VALUE = new Long(1L);
    public static final Long UOM_ID = new Long(2L);

    IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullConvert() {
        assertNull(null,converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void testConvertNullUOM() {

        Ingredient ingredient = new Ingredient();
        ingredient.setRecipe(RECIPE);
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setUnitOfMeasure(null);

        IngredientCommand command = new IngredientCommand();

        command = converter.convert(ingredient);

        assertNotNull(command.getId());
        assertNull(command.getUnitOfMeasure());
        assertEquals(ID_VALUE,command.getId());
        assertEquals(DESCRIPTION,command.getDescription());
        assertEquals(AMOUNT,command.getAmount());
        assertNull(command.getUnitOfMeasure());
    }

    @Test
    public void testIngredientWithUOM() {

        Ingredient ingredient = new Ingredient();
        ingredient.setRecipe(RECIPE);
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);

        ingredient.setUnitOfMeasure(uom);

        IngredientCommand command = new IngredientCommand();

        command = converter.convert(ingredient);

        assertNotNull(command.getId());
        assertNotNull(command.getUnitOfMeasure());
        assertEquals(ID_VALUE,command.getId());
        assertEquals(DESCRIPTION,command.getDescription());
        assertEquals(AMOUNT,command.getAmount());
        assertEquals(UOM_ID,command.getUnitOfMeasure().getId());

    }
}
