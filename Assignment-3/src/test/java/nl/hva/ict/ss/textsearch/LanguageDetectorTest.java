package nl.hva.ict.ss.textsearch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import static junit.framework.TestCase.*;

/**
 * @author Costa van Elsas
 */
public class LanguageDetectorTest {
    private LanguageDetector detector;
    private LanguageDetector detectorDutch;
    private LanguageDetector detectorSpanish;

    @Before
    public void setup() {
        detector = new LanguageDetector(getClass().getResourceAsStream("/edu/princeton/cs/algs4/Huffman.java"));
        detectorDutch = new LanguageDetector(getClass().getResourceAsStream("/edu/princeton/cs/algs4/Dutch.txt"));
        detectorSpanish= new LanguageDetector(getClass().getResourceAsStream("/edu/princeton/cs/algs4/spanish.txt"));
        detector.isEnglishText();
    }

    /**
     * test to see if the text is english
     */
    @Test
    public void testIsEnglish() {
        System.out.println(detector.isEnglishText());
        assertTrue(detector.isEnglishText());
    }

    /**
     * test to see if the text is not english
     */
    @Test
    public void testIsDutch() {
        System.out.println(detectorDutch.isEnglishText());
        assertFalse(detectorDutch.isEnglishText());
    }

    /**
     * test to see if the text is not english
     */
    @Test
    public void testIsSpanish() {
        System.out.println(detectorSpanish.isEnglishText());
        assertFalse(detectorSpanish.isEnglishText());
    }
}