package com.stanson.anagram.debug;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Boilerplate - TODO please change all documentation
 *
 * Created by yourname on date
 */
public class AnagramTest {

    /**
     * Command line entry point. Starts the anagram test and prints
     * the result
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Anagram Test");
        JUnitCore runner = new JUnitCore();
        runner.addListener(new TextListener(System.out));
        Result result = runner.run(AnagramTest.class);
        System.out.println(String.format("%d tests run in %d msec. %s failures.\n\n", result.getRunCount(),
                result.getRunTime(), result.getFailureCount()));
    }

    /**
     * Test Adding values to the data structure
     */
    @Test
    public void testAdd() {

        Anagram testObject = new Anagram();

        // First some bounds testing
        try {
            testObject.add(null);
            fail("Null should cause an exception");
        } catch (Exception e) {

        }
        try {
            testObject.add("");
            fail ("Blank should cause an exception");
        } catch (Exception e) {
        }
        try {
            testObject.add("fasfkKKKJ3KJDLK");
            fail("Non alpha should cause an exception");
        } catch (Exception e) {
        }

        // Simple word
        testObject.add("a");
        // long word
        testObject.add("alfkdjaolfiejwoqirejlikjfalkdjlksdjflkasjflkdsajflkdasjfldksaj");
    }

    /**
     * Test boundaries of FindAnagrams
     *
     * @throws Exception Any exception causes the test to fail
     */
    @Test
    public void testFindAnagramsBoundary() throws Exception {

        Anagram testObject = new Anagram();
        testObject.add("lens");
        testObject.add("no");
        testObject.add("on");

        try {
            testObject.findAnagrams(null);
            fail("Null should cause an exception");
        } catch (Exception e) {
        }

        try {
            testObject.findAnagrams("   ");
            fail("Blank should cause an exception");
        } catch (Exception e) {
        }

        try {
            testObject.findAnagrams("fasfkKKKJ3KJDLK");
            fail("Non alpha should cause an exception");
        } catch (Exception e) {
        }

        assertTrue(testObject.findAnagrams("a").size() == 0);
    }


    // TODO add test cases

}
