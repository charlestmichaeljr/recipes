package com.charlie.recipes.converters;

import com.charlie.recipes.commands.CategoryCommand;
import com.charlie.recipes.domain.Category;
import lombok.Synchronized;
import org.hibernate.annotations.Synchronize;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category,CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {

        if(source == null) {
            return null;
        }

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setDescription(source.getDescription());
        categoryCommand.setId(source.getId());

        return categoryCommand;
    }
}
