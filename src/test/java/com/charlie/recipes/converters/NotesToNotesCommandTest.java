package com.charlie.recipes.converters;

import com.charlie.recipes.commands.NotesCommand;
import com.charlie.recipes.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class NotesToNotesCommandTest {

    final Long NOTES_ID = new Long(1L);
    final String RECIPE_NOTES = "Some notes";

    NotesToNotesCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() {

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        notes.setRecipeNotes(RECIPE_NOTES);

        NotesCommand command = converter.convert(notes);

        assertNotNull(command);
        assertEquals(NOTES_ID,command.getId());
        assertEquals(RECIPE_NOTES,command.getRecipeNotes());
    }
}
