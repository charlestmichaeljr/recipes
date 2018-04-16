package com.charlie.recipes.converters;

import com.charlie.recipes.commands.CategoryCommand;
import com.charlie.recipes.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CategoryCommandToCategoryTest {

    private final Long CATEGORY_ID = 1L;
    private final String DESCRIPTION = "Some Description";

    CategoryCommandToCategory converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    public void testEmptyObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {

        CategoryCommand command = new CategoryCommand();
        command.setId(CATEGORY_ID);
        command.setDescription(DESCRIPTION);

        Category category = converter.convert(command);

        assertNotNull(category);
        assertEquals(CATEGORY_ID,category.getId());
        assertEquals(DESCRIPTION,category.getDescription());
    }
}
