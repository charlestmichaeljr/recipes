package com.charlie.recipes.converters;

import com.charlie.recipes.commands.NotesCommand;
import com.charlie.recipes.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class NotesCommandToNotesTest {

    final Long NOTES_ID = new Long(1L);
    final String RECIPE_NOTES = "Some notes";

    NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() {
        NotesCommand command = new NotesCommand();
        command.setId(NOTES_ID);
        command.setRecipeNotes(RECIPE_NOTES);

        Notes notes = converter.convert(command);

        assertNotNull(notes);
        assertEquals(NOTES_ID,command.getId());
        assertEquals(RECIPE_NOTES,command.getRecipeNotes());
    }
}
