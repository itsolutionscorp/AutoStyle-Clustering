#William Morris
#exercism.io
#anagram.py

from collections import Counter

def detect_anagrams(word, test_list):
    return [test for test in test_list if test.lower()!= word.lower() and
            Counter(word.lower()) == Counter(test.lower())]
