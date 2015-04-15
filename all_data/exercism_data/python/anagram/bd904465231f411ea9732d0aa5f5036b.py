__author__ = 'agupt15'


def detect_anagrams(word, lst):
    return [element for element in lst if is_anagram(word.upper(), element.upper())]


def is_anagram(word1, word2):
    if word1 == word2:
        return False
    return sorted(word1) == sorted(word2)
