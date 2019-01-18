package nl.hva.ict.ss.textsearch;

import org.junit.Before;
import org.junit.Test;

public class LanguageDetectorTest {
    private LanguageDetector detector;

    @Before
    public void setup() {
        detector = new LanguageDetector(getClass().getResourceAsStream("/edu/princeton/cs/algs4/Huffman.java"));
        detector.isEnglishText();
    }

    // Add your tests here. They are allowed to NOT use assertXxxx... :-)
    /*
    TODO: add extra tests with text files of english spanish and dutch and greek
     */
    @Test
    public void test() {
        System.out.println(detector.isEnglishText());


    }
}