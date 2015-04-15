from collections import Counter

def detect_anagrams(word, candidates):
    """Returns list of candidates that are anagrams of word"""
    return [candidate for candidate in candidates if Counter(candidate.lower()) == Counter(word.lower()) and candidate.lower() != word.lower()]
