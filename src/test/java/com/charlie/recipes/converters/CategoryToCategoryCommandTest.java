package com.charlie.recipes.converters;

import com.charlie.recipes.commands.CategoryCommand;
import com.charlie.recipes.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CategoryToCategoryCommandTest {

    private final Long CATEGORY_ID = 1L;
    private final String DESCRIPTION = "Some Description";

    CategoryToCategoryCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() {

        Category category = new Category();
        category.setId(CATEGORY_ID);
        category.setDescription(DESCRIPTION);

        CategoryCommand command = converter.convert(category);

        assertNotNull(command);
        assertEquals(CATEGORY_ID,command.getId());
        assertEquals(DESCRIPTION,command.getDescription());
    }
}
