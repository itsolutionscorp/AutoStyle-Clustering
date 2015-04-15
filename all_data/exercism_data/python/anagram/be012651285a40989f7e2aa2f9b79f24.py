from collections import Counter


class Anagram(object):
    """Recognize anagrams of a specific word"""

    def __init__(self, word):
        """Save the word (and a letter count) for later use"""
        self.word = word
        self._word_counter = Counter(word.lower())

    def is_anagram(self, test_word):
        """Returns True if test_word is an anagram of self.word"""
        if test_word == self.word:
            return False  # Words aren't anagrams of themselves.
        return Counter(test_word.lower()) == self._word_counter

    def match(self, choices):
        """Return any anagrams of the initial word"""
        return filter(self.is_anagram, choices)
