__author__ = 'gdunn'

from collections import defaultdict


def detect_anagrams(word, candidates):
    """
    :rtype : list
    :param word: str
    :param candidates:
    """
    anagrams = []
    word_letters = defaultdict(int)
    candidate_letters = defaultdict(int)

    for letter in word:
        word_letters[letter.lower()] += 1

    for candidate in candidates:
        if candidate.lower() != word.lower():
            for letter in candidate:
                candidate_letters[letter.lower()] += 1
            if candidate_letters == word_letters:
                anagrams.append(candidate)
        candidate_letters.clear()

    return anagrams
