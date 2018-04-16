package com.charlie.recipes.converters;

import com.charlie.recipes.commands.IngredientCommand;
import com.charlie.recipes.commands.UnitOfMeasureCommand;
import com.charlie.recipes.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient,IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand converter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.converter = converter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {

        if(source == null) {
            return null;
        }

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setDescription(source.getDescription());
        ingredientCommand.setAmount(source.getAmount());
        ingredientCommand.setId(source.getId());
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        ingredientCommand.setUnitOfMeasure(converter.convert(source.getUnitOfMeasure()));

        return ingredientCommand;
    }
}
