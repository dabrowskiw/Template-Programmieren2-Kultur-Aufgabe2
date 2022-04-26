package org.htw.prog2.aufgabe1;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MutationPatternsTest {

    @Test
    void parseSusceptibilities() {
    }

    @Test
    void parseDrugs_wrongFirstElement() {
        assertThrows(FileFormatException.class, () -> {
            MutationPatterns.parseOrganisms("\"Mutation\";\"Chimp GS\";\"Human GS\"");
        });
    }

    @Test
    void parseDrugs_wrongOrganismNames() {
        assertThrows(FileFormatException.class, () -> {
            MutationPatterns.parseOrganisms("\"Mutation Pattern\";\"Human\";\"Chimp GS\";\"Neanderthal GS\"");
        });
        assertThrows(FileFormatException.class, () -> {
            MutationPatterns.parseOrganisms("\"Mutation Pattern\";\"Human GS\";\"ChimpGS\";\"Neanderthal GS\"");
        });
    }

    @Test
    void parseOrganisms() {
        assertDoesNotThrow(() -> {
            List<String> drugs = MutationPatterns.parseOrganisms("\"Mutation Pattern\";\"Human GS\";\"Chimp GS\";\"Neanderthal GS\"");
            LinkedList<String> expected = new LinkedList<>();
            expected.add("Human");
            expected.add("Chimp");
            expected.add("Neanderthal");
            assertEquals(expected, drugs);
        });
    }

    @Test
    void constructor_doesNotExist() {
        assertThrows(FileNotFoundException.class, () -> {
            new MutationPatterns("data/IDONOTEXIST");
        });
    }

    @Test
    void constructor_wrongHeaderStart() {
        assertThrows(FileFormatException.class, () -> {
            new MutationPatterns("data/GREB1_patterns_wrongHeader.csv");
        });
    }

    @Test
    void constructor_wrongHeaderDrugnames() {
        assertThrows(FileFormatException.class, () -> {
            new MutationPatterns("data/GREB1_patterns_wrongHeader2.csv");
        });
    }

    @Test
    void constructor_columnNumbers() {
        assertThrows(FileFormatException.class, () -> {
            new MutationPatterns("data/GREB1_patterns_wrongElementNumber.csv");
        });
    }

    @Test
    void constructor_correctFile() {
        assertDoesNotThrow(() -> {
            new MutationPatterns("data/GREB1_patterns.csv");
        });
    }

    @Test
    void getNumberOfMutations() {
        assertDoesNotThrow(() -> {
            MutationPatterns patterns = new MutationPatterns("data/GREB1_patterns.csv");
            assertEquals(4, patterns.getNumberOfMutations());
        });
    }

}