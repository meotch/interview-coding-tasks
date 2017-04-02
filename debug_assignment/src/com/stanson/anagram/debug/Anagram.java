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
 * Created by Mitchell Williams on 4/1/17
 */
public class Anagram {

    /** The logger */
    static final Logger LOGGER = Logger.getLogger(Anagram.class.getName());

    private class TrieNode
    {
        public Character character;

        public int charIndex;

        public TrieNode[] children = null;//Node initialized w/ no children
        
        public Boolean isValid = false;//Used to signal valid words to fix early exit error
    }

    private TrieNode root = new TrieNode();//create Trie

    /**
     * Add a word to the data structure holding the dictionary.
     *
     * Performance: O(L)	L = length of word		
     *
     * Size: O()
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
        
        //Add word to Trie
        TrieNode node = root;
        char[] charArray = word.toLowerCase().toCharArray();
        //Add TrieNode per char in word
        for (int idx=0; idx < charArray.length;idx++)
        {
            Character c = charArray[idx];
            int cIndex = c-97;
            if (node.children == null)//Create children TrieNode[] if it has not been set
            {
                node.children = new TrieNode[26];
            }
            TrieNode child = node.children[cIndex];
            if (child == null)//Create child TrieNode
            {
                child = new TrieNode();
                child.character = c;
                child.charIndex = cIndex;
                node.children[cIndex] = child;
            }
            node = child;//Traverse down Trie
        }
        node.isValid = true;
    }

    /**
     * <pre>
     * Find all anagrams for the provided phrase. The anagrams will only
     * contain those words which have been added to this Trie. All permutations
     * of multiple word annotations will be provided and a single word can
     * appear in a single anagram multiple times.
     *
     * Performance: O(M log N)	M = Maximum word length		N = # of Chars in tree
     *
     * @param phrase The phrase to check for anagrams.
     * @return A list of anagrams to the phrase. If none are found, an empty list will be returned.
     * </pre>
     */
    public List<String> findAnagrams(String phrase)
    {
        if (StringUtils.isBlank(phrase))//Reject empty phrase
        {
            throw new IllegalArgumentException("Parameter phrase must not be blank or null");
        }
        String search = phrase.toLowerCase().replaceAll("[ \t\n]", "");//remove spaces from phrase
        if (!StringUtils.isAlpha(search))//Reject non-Alpha chars
        {
            throw new IllegalArgumentException("Parameter phrase must be only alphabetic characters, spaces, and tabs");
        }

        int[] charCount = new int[26];
        for (Character c : search.toCharArray()) {//iterate through phrase
            charCount[(c - 97)] ++;//count chars and store in position relative to alphabet ie charCount[0] = # of a, charCount[25] = # of z
        }

        ArrayList<String> result = new ArrayList<String>();
        anagramHelper(root, charCount, 0, search.length(), "", result);

        return result;
    }

    /**
     * <pre>
     * Recursive fct traverses Trie, adding valid anagram combos to result
     * 
     * NOTE* changed return from void to ArrayList<String> so result can get passed back from recursion due to Java passing Objects as references by value
     * 
     * </pre>
     * @param node - TrieNode of Trie being traversed
     * @param charCount - Array tracks available chars for anagrams
     * @param currentLen - Level of Trie == Current length of potential anagram inprocessResult
     * @param maxLen - Determines how deep anagramHelper traverses Trie
     * @param inprocessResult - Potential/partial anagram to track TrieNode.charaters traversed
     * @param result - Used to pass currentList of anagrams down Trie
     * @return ArrayList - Used to pass currentList of anagrams UP Trie
     */
    private ArrayList<String> anagramHelper(TrieNode node, int[] charCount, int currentLen, final int maxLen, String inprocessResult, ArrayList<String> result)
    {
    	//End of branch
        if (node.children == null || node.isValid) {
            if (currentLen == maxLen) {//Valid anagram found
                result.add(inprocessResult);//Store anagram
            } else {//Unfinished anagram
                if (root.children != null) {//More branches to traverse exist
                    for (TrieNode child : root.children) {
                        if (child != null && charCount[child.charIndex] > 0) {//child.character is valid for potential anagram
                            charCount[child.charIndex]--;//'pop' char off charCount
                            String subAnagram = inprocessResult + " " + child.character;//add char to subAnagram 
                            
                            /**
                             * Made anagramHelper return ArrayList<String> due to Java passing Objects as references by value (meaning changes don't get passed up as anagramHelper() completes)
                             * 		*Solved problem w/ result not getting returned
                             * Made charCount push characters back onto array after anagramHelper() returns 
                             * 		*Solved problem w/ only 1 anagram being found (charCount would never get 'reset', so it wouldn't traverse the Trie properly)
                             */
                            result = anagramHelper(child, charCount, currentLen + 1, maxLen, subAnagram, result);//Traverse down Trie to child		
                            charCount[child.charIndex]++;//'push' char on charCount		
                        }
                    }
                }
            }
        }
        
        //NOT end of branch
        if (currentLen < maxLen && node.children != null) {
            for (TrieNode child : node.children) {
                if (child != null && charCount[child.charIndex] > 0) {//child.character is valid for potential anagram 
                    charCount[child.charIndex]--;//'pop' char off charCount
                    String subAnagram = inprocessResult + child.character;//add char to subAnagram
                    
                    /**
                     * Made anagramHelper return ArrayList<String> due to Java passing Objects as references by value (meaning changes don't get passed up as anagramHelper() completes)
                     * 		*Solved problem w/ result not getting returned
                     * Made charCount push characters back onto array after anagramHelper() returns 
                     * 		*Solved problem w/ only 1 anagram being found (charCount would never get 'reset', so it wouldn't traverse the Trie properly)
                     */
                    result = anagramHelper(child, charCount, currentLen + 1, maxLen, subAnagram, result);//Traverse down Trie to child
                    charCount[child.charIndex]++;//'push' char on charCount
                }
            }
        }
        return result;
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
        //Create Anagram object
        Anagram anagramFinder = new Anagram();
        //Set start and Load dictionary from passed in argument
        long start = System.currentTimeMillis();
        anagramFinder.readFile(dictionaryFile);
        if (LOGGER.isLoggable(Level.FINER))
        {
            LOGGER.finer("Dictionary load time: " + (System.currentTimeMillis() - start) + " ms");
        }
        //Reset start and findAnagrams()
        start = System.currentTimeMillis();
        List<String> anagrams = anagramFinder.findAnagrams(phrase);
        if (LOGGER.isLoggable(Level.FINER))
        {
            LOGGER.finer("Anagram time: " + (System.currentTimeMillis() - start) + " ms");
            LOGGER.finer("Anagram results: " + anagrams.size());
        }

        //Display Anagrams to console
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
