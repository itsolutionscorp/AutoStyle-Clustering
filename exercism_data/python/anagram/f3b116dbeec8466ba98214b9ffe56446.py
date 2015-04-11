"""
Has two functions that work together to determine
a list of anagrams from one word and a list of
potential anagram candidates.
"""


def check(word_one, word_two):
    """
    check() takes two words and sees if they are anagrams
    of one another. the same word isn't an anagram of itself
    but any other word spelled with the same letters is.
    """
    if word_one.lower() == word_two.lower():
        return False
    return sorted(word_one.lower()) == sorted(word_two.lower())


def detect_anagrams(word_one, candidates):
    """
    detect_anagrams() takes a word and a list of potential
    anagram candidates. It returns a list of all candidates
    that pass check() for word one and word two.
    """
    return [word_two for word_two in candidates if check(word_one, word_two)]
