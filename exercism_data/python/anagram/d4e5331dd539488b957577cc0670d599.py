from collections import Counter
from functools import partial 

def is_anagram(word1, word2):
    word1 = word1.lower()
    word2 = word2.lower()
    return word1 != word2 and Counter(word1) == Counter(word2)

def detect_anagrams(word, candidates):
    _is_anagram = partial(is_anagram, word)
    return filter(_is_anagram, candidates)
