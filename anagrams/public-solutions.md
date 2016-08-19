## WTF?

This file is to provide pointers to easily-found solutions to these problems. The goal isn't to discourage googling, but to recognize [copypasta](https://en.wiktionary.org/wiki/copypasta#Noun).

### Python

[2012 stackoverflow](http://stackoverflow.com/questions/11989502/producing-all-the-anagrams-from-a-string-python):

    ["".join(perm) for perm in itertools.permutations("abc")]

    for perm in all_perms(elements[1:]):
      for i in range(len(elements)):
        yield perm[:i] + elements[0:1] + perm[i:]

    for perm in all_perms(elements[1:]):
      for i in range(len(elements)):
        tmp.append(perm[:i] + elements[0:1] + perm[i:])
