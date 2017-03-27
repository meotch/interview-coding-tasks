package com.stanson.anagram.debug;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation with bugs - TODO please change all documentation
 *
 * Created by yourname on date
 */
public class Anagram {

    /** The logger */
    static final Logger LOGGER = Logger.getLogger(Anagram.class.getName());

    private class TrieNode
    {
        public Character character;

        public int charIndex;

        public TrieNode[] children = null;
    }

    private TrieNode root = new TrieNode();

    /**
     * Add a word to the data structure holding the dictionary.
     *
     * Performance: O(x) // provide an estimate
     *
     * Size: O(x) // provide an estimate
     *
     * @param word The word to add. Should be in ASCII
     */
    public void add(String word)
    {
        if (StringUtils.isBlank(word))
        {
            throw new IllegalArgumentException("Parameter word must not be blank or null");
        }
        if (!StringUtils.isAlpha(word))
        {
            throw new IllegalArgumentException("Parameter word must be only alphabetic characters");
        }

        TrieNode node = root;
        char[] charArray = word.toLowerCase().toCharArray();

        for (int idx=0; idx < charArray.length;idx++)
        {
            Character c = charArray[idx];
            int cIndex = c-97;

            if (node.children == null)
            {
                node.children = new TrieNode[26];
            }

            TrieNode child = node.children[cIndex];
            if (child == null)
            {
                child = new TrieNode();
                child.character = c;
                child.charIndex = cIndex;
                node.children[cIndex] = child;
            }

            node = child;
        }
    }

    /**
     * Find all anagrams for the provided phrase. The anagrams will only
     * contain those words which have been added to this Trie. All permutations
     * of multiple word annotations will be provided and a single word can
     * appear in a single anagram multiple times.
     *
     * Performance: O( )  // provide an estimate
     *
     * @param phrase The phrase to check for anagrams.
     * @return A list of anagrams to the phrase. If none are found, an empty list will be returned.
     */
    public List<String> findAnagrams(String phrase)
    {
        if (StringUtils.isBlank(phrase))
        {
            throw new IllegalArgumentException("Parameter phrase must not be blank or null");
        }
        String search = phrase.toLowerCase().replaceAll("[ \t\n]", "");
        if (!StringUtils.isAlpha(search))
        {
            throw new IllegalArgumentException("Parameter phrase must be only alphabetic characters, spaces, and tabs");
        }

        int[] charCount = new int[26];
        for (Character c : search.toCharArray()) {
            charCount[(c - 97)] ++;
        }

        ArrayList<String> result = new ArrayList<String>();
        anagramHelper(root, charCount, 0, search.length(), "", result);

        return result;
    }

    private void anagramHelper(TrieNode node, int[] charCount, int currentLen, final int maxLen, String inprocessResult, List<String> result)
    {
        if (node.children == null) {
            if (currentLen == maxLen) {
                result.add(inprocessResult);
            } else {
                if (root.children != null) {
                    for (TrieNode child : root.children) {
                        if (child != null && charCount[child.charIndex] > 0) {
                            charCount[child.charIndex]--;
                            String subAnagram = inprocessResult + " " + child.character;
                            anagramHelper(child, charCount, currentLen + 1, maxLen, subAnagram, result);
                        }
                    }
                }
            }
        }

        if (currentLen < maxLen && node.children != null) {
            for (TrieNode child : node.children) {
                if (child != null && charCount[child.charIndex] > 0) {
                    charCount[child.charIndex]--;
                    String subAnagram = inprocessResult + child.character;

                    anagramHelper(child, charCount, currentLen + 1, maxLen, subAnagram, result);
                }
            }
        }
    }

    /**
     * Command line entry point. Starts the anagram functionality and prints
     * the result
     *
     * @param args The command line arguments
     * @throws Exception Thrown if there is an issue reading the file
     */
    public static void main(String[] args) throws IOException {

        if (args == null || args.length != 2)
        {
            System.out.println("Anagram finder\n\n");
            System.out.println("To run:");
            System.out.println("./run.sh <phrase> <dictionaryfile>");
            return;
        }
        String phrase = args[0];
        String dictionaryFile = args[1];

        Handler console = new ConsoleHandler();
        console.setLevel(Level.FINER);
        LOGGER.addHandler(console);
        LOGGER.setLevel(Level.FINER);

        Anagram anagramFinder = new Anagram();

        long start = System.currentTimeMillis();
        anagramFinder.readFile(dictionaryFile);
        if (LOGGER.isLoggable(Level.FINER))
        {
            LOGGER.finer("Dictionary load time: " + (System.currentTimeMillis() - start) + " ms");
        }

        start = System.currentTimeMillis();
        List<String> anagrams = anagramFinder.findAnagrams(phrase);
        if (LOGGER.isLoggable(Level.FINER))
        {
            LOGGER.finer("Anagram time: " + (System.currentTimeMillis() - start) + " ms");
            LOGGER.finer("Anagram results: " + anagrams.size());
        }

        // Doing this the simple way. Could use a lib to do it, but for this
        // case it is of limited value.
        System.out.println("{");
        System.out.println("  \"inputPhrase\":\""+phrase+"\",");
        System.out.println("  \"dictionary\":\""+dictionaryFile+"\",");
        System.out.println("  \"anagrams\": [");
        String prefix = "\n                 \"";
        String postfix= "\"";
        for (String a: anagrams)
        {
            System.out.print(prefix+a+postfix);

            prefix=",\n                 \"";
        }
        System.out.println("\n  ]");
        System.out.println("}");
    }

    /**
     * Read a dictionary file and add the words to the AnagramTrie
     *
     * @param fileName The file name of the dictionary file.
     * @throws IOException Thrown if there is an issue reading the file
     */
    private void readFile(String fileName) throws IOException {
        LineIterator it = FileUtils.lineIterator(new File(fileName), "UTF-8");
        int loaded = 0;
        try {
            while (it.hasNext()) {
                String line = it.nextLine();
                add(line);
                loaded++;
            }
        } finally {
            LineIterator.closeQuietly(it);
        }

        if (LOGGER.isLoggable(Level.FINER))
        {
            LOGGER.finer("Loaded "+loaded+" words");
        }
    }
}
