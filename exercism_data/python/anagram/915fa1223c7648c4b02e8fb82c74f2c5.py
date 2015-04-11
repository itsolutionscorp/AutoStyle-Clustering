"""
A module for finding anagrams from a word and a list of candidates
"""
from collections import Counter


class Anagram(object):
    def __init__(self, word):
        self.word = word.lower()
        self.letter_count = self._count_letters(self.word)

    def _count_letters(self, word):
        return Counter([letter for letter in word])

    def _match_word(self, word):
        if word != self.word and self._count_letters(word) == self.letter_count:
            return True
        return False

    def match(self, word_list):
        return [word for word in word_list if self._match_word(word.lower())]
