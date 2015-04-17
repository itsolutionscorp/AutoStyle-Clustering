#!/usr/bin/env python
def detect_anagrams(test_word, group):
    abc_word = ''.join(sorted(test_word.lower()))
    anagrams = []
    for word in group:
        if (abc_word == ''.join(sorted(word.lower())) and 
            test_word.lower() != word.lower()):
            anagrams.append(word)
    return anagrams
