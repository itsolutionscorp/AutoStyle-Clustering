#William Morris
#exercism.io
#anagram.py

from collections import Counter

def detect_anagrams(word, test_list):
    anagram_list = []
    for test in test_list:
        test_lower = test.lower()
        word = word.lower()
        if test_lower!=word and Counter(word) == Counter(test_lower):
            anagram_list += [test]
    return anagram_list

