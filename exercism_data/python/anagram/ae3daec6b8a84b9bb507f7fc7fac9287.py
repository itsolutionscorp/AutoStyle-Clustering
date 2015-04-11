from functools import partial


def is_anagram_of(word1, word2):
    """
    Case insensitive anagram detector.

    Returns True if word1 is an anagram of (but not equal to) word2
    """
    word1 = word1.lower()
    word2 = word2.lower()
    return word1 != word2 and sorted(word1) == sorted(word2)


def detect_anagrams(word, anagrams):
    return filter(partial(is_anagram_of, word), anagrams)
