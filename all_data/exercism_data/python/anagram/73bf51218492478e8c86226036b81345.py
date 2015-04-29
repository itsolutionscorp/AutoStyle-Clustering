__author__ = 'cameron'


def detect_anagrams(word, possibles):
    anagram = []
    sortedpossibles = sorted(word.lower())

    for alt in possibles:
        if sortedpossibles == sorted(alt.lower()):
            anagram.append(alt)

    return anagram
