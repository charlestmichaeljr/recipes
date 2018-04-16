package com.charlie.recipes.converters;

import com.charlie.recipes.commands.CategoryCommand;
import com.charlie.recipes.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand,Category> {


    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {

        if(source == null) {
            return null;
        }

        Category category = new Category();
        category.setDescription(source.getDescription());
        category.setId(source.getId());

        return category;
    }
}
