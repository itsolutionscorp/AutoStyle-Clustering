__author__ = 'gdunn'
from collections import Counter
def detect_anagrams(word, candidates):
    """
    :rtype : list
    :param word: str
    :param candidates:
    """
    anagrams = []
    for candidate in candidates:
        if candidate.lower() != word.lower() and Counter(candidate.lower()) == Counter(word.lower()):
            anagrams.append(candidate)
    return anagrams
