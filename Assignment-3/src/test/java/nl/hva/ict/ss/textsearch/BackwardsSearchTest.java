package nl.hva.ict.ss.textsearch;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Costa van Elsas
 */
public class BackwardsSearchTest {
    protected BackwardsSearch searchEngine;

    @Before
    public void setup() {
        searchEngine = new BackwardsSearch();
    }

    @Test
    public void findSingleOccurrence() {
        int index = searchEngine.findLocation("needle", "whereistheneedleinthishaystack");

        assertEquals("whereisthe".length(), index);
    }

    @Test
    public void cantFindOccurrence() {
        int index = searchEngine.findLocation("needle", "thereisnothinginthishaystack");

        assertEquals(-1, index);
    }

//    @Test
//    public void getComparissons() {
//        int index = searchEngine.findLocation("needle", "whereohwhereistheneedleinthishaystack");
//
//        int amount = searchEngine.getComparisonsForLastSearch(index);
//
//        assertEquals(5, amount);
//    }
}