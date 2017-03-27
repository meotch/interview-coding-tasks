# Anagram Debugging Problem

An [anagram](https://en.wikipedia.org/wiki/Anagram) requires letters to be rearranged into new words or phrases. Specifically, output anagrams should be valid words in a chosen language.

The project that you are working on requires a solution which will produce all anagrams for a provided phrase from a dictionary of words. It must:
* Work with multi-word phrases
* Provide all permutations of multi-word anagrams (ie 'no lens' and 'lens no' might be output from a phrase of 'nelson')
* Within multi-word anagrams, the same word can be used multiple times (ie 'a a i' might be output from a phrase of 'aai')

## Goal
You have been given a partially working solution to this problem and are being asked to debug and test the solution.

## Solution Background

The solution is based on a [Trie data structure](https://en.wikipedia.org/wiki/Trie) which is used to efficiently store the dictionary such that anagrams can be easily retrieved.

There's no need to handle case or any non-ascii characters (spaces, punctuation, diacritics); feel free to normalize or assume simple lowercase ascii input. Spaces can be freely added or removed, so "indicatory" has anagrams of "dictionary" and "rant idiocy".

The dictionary to be used can be found in the dictionary subdirectory and is adapted from [the New General Service List](http://www.newgeneralservicelist.org/). 


## Development Environment Requirements

* Java 8
* Junit, Apache Commons Libraries are acceptable
* IDE/Editor of your choice

## Expected Outcome

1. Document the code - both existing and any additional code you add
2. Correct code with corrections indicated via comments
3. Add unit tests which demonstrate that the code works correctly
4. Describe how the number of operations increases with the number of items in the dictionary.  If you’re familiar, please use Big O notation
5. Describe how the number of operations increases with the length of the phrase to be anagrammed.  If you’re familiar, please use Big O notation
6. Provide a few example executions of the utility with the resulting output

## Test Cases

In addition to the tests that have been provided, you will need to build a set of tests which in general ensure that the output anagrams are appropriate for a given dictionary.
Assume that this is being used in a service which is continuously deployed. It is imperitive to have high test coverage to avoid production impacts. Test cases should cover all of the functional requirements above.
In addition it has been noted that some words like 'as' don't always end up in the result when they should.

# Project Structure

The project is set up to require limited dependencies and minimal changes other than the code that is requested. Feel free to utilize your own IDE, but please ensure the scripts work as expected.

To execute the tests:

    ./test.sh

Example run:

    ./run.sh "my phrase" dictionary/new-general-service-list-words.txt


# License
As with all content and code included in this repository, it is licensed under CC-BY (content) and the MIT license found in the root of this repository. The New General Service List is licensed under [Creative Commons](http://creativecommons.org/licenses/by-sa/4.0/)
