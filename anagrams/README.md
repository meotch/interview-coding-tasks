# Anagram checker
An [anagram](https://en.wikipedia.org/wiki/Anagram) requires letters to be rearranged into new words or phrases. Specifically, output anagrams should be valid words in a chosen language.

## Goals
This is an interesting problem in terms of **performance**. What is the performance characteristic of the letter permutation? What is the performance characteristic of validity checking? How much memory does this use?

This problem can also demonstrate effective **unit tests**.

## Input
The input file will be given on stdin and will contain a list of input phrases, one per line.

The dictionary to be used is [the 10,000 most common English words](https://github.com/first20hours/google-10000-english). The size of this list was inspired by [an XKCD comic](https://xkcd.com/1133/); it's nice because you don't have to use a corpus of all words.

There's no need to handle case or any non-ascii characters (spaces, punctuation, diacritics); feel free to normalize or assume simple lowercase ascii input. Spaces can be freely added or removed, so "indicatory" has anagrams of "dictionary" and "rant idiocy".

## Expected output
Script should print the input strings and valid outputs, as JSON.

### Unit tests
In addition to solving the problem, your solution **must** include basic unit tests using a common framework in the language of your choice. This should not require access to external resources, such as services/APIs or files outside of your repository.

A bootstrap file or jsfiddle link should exist in this directory for the language of your choice.

### Optional: visualization

Use (TODO MARTIJN library) to produce a self-contained HTML file giving a [histogram](http://en.wikipedia.org/wiki/Histogram) displaying how many anagrams were generated per word. By "self-contained", the numeric data should be contained within the file- it's okay to use `<script>` for libraries.

## Restrictions
Use the language specified by your interviewer. Generally you should not use extended libraries/packages. This doesn't mean you need to reinvent the wheel, but sample problems shouldn't need complicated libraries.

### Extended libraries allowed by language:

* **Java**: reusable components from [Apache Commons](http://commons.apache.org/components.html) are acceptable, as is [GSON](https://code.google.com/p/google-gson/).
* **Python**: `requests` should be the only nonstandard library needed.
* **Javascript**: todo

## Example

As an example, this command should output anagrams on most platforms, given a dictionary file:

    $ echo "nelson" | ./anagram.py /path/to/dict
    nelson=lens no,lens on

# Source
Upstream on this is [a version at adhoclabs that tedder created](https://github.com/adhoclabs/interview-code-tests/blob/master/anagrams/README.md), based on [streamtech](http://www.streamtech.nl/problemset/148.html) for the idea.

# License
As with all content and code included in this repository, it is licensed under CC-BY (content) and the MIT license found in the root of this repository.

