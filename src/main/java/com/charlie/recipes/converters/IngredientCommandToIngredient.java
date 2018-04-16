package com.charlie.recipes.converters;

import com.charlie.recipes.commands.IngredientCommand;
import com.charlie.recipes.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand,Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Override
    public Ingredient convert(IngredientCommand source) {
        if(source == null){
            return null;
        }

        final Ingredient ingredient = new Ingredient();
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setId(source.getId());
        ingredient.setUnitOfMeasure(uomConverter.convert(source.getUnitOfMeasure()));


        return ingredient;
    }
}
