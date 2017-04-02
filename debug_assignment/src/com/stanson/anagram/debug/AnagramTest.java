package com.stanson.anagram.debug;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * <pre>
 * Tests
 * 1. testAdd - Adding words to Trie
 * 2. testFindAnagramBoundary - Finding anagram boundaries in Trie
 *
 * Created by Mitchell Williams on 4/1/17
 * </pre>
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

        try {
        	testObject.add("Two words");
        	fail("Spaces should cause an exception");
        } catch (Exception e) {
        }
        //addNull bounds test
        try {
            testObject.add(null);
            fail("Null should cause an exception");
        } catch (Exception e) {

        }
        //addBlank bounds test
        try {
            testObject.add("");
            fail ("Blank should cause an exception");
        } catch (Exception e) {
        }
        //addAlpha bounds test
        try {
            testObject.add("fasfkKKKJ3KJDLK");
            fail("Non alpha should cause an exception");
        } catch (Exception e) {
        }
        //addWords bounds test
        try {
        	testObject.add("Two words");
        	fail("Spaces should cause an exception");
        } catch (Exception e) {
        }
        
        // Simple word
        testObject.add("a");
        // long word
        testObject.add("alfkdjaolfiejwoqirejlikjfalkdjlksdjflkasjflkdsajflkdasjfldksaj");
        // UpperCase word
        testObject.add("ALLCAPSALLTHETIME");
    }

    /**
     * Test boundaries of findAnagrams()
     *
     * @throws Exception Any exception causes the test to fail
     */
    @Test
    public void testFindAnagramsBoundary() throws Exception {

    	//Create Trie and load w/ words
        Anagram testObject = new Anagram();
        
        testObject.add("meow");
        testObject.add("me");
        testObject.add("lens");
        testObject.add("no");
        testObject.add("on");
        testObject.add("fighter");
        testObject.add("all");
        testObject.add("a");

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
        
        assertTrue(testObject.findAnagrams("me").size() == 1);//Validates adding a valid subword of an already existant branch in Trie works
        assertTrue(testObject.findAnagrams("al").size() == 0);//Validates that random char combos as valid Trie traversal combos are NOT anagrams
        assertTrue(testObject.findAnagrams("fight").size() == 0);//Validates that only explicitly added words can be used as anagrams
        assertTrue(testObject.findAnagrams("aaallllll").size() == 1);//Validates order of phrase doesn't matter AND same word can be used multiple times
        assertTrue(testObject.findAnagrams("a").size() == 1);//Validates subTrie branches ie "a" in "a"-"l"-"l" is a valid 
        assertTrue(testObject.findAnagrams("nelson").size() == 4);//Validates Permutations of anagrams work
    }
}
