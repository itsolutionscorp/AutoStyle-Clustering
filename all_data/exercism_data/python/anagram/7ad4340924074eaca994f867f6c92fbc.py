from collections import Counter


class Anagram(object):
    """Recognize anagrams of a specific word"""

    def __init__(self, word):
        """Save the word for later use"""
        self.word = word

    def match(self, choices):
        """Return any anagrams of the initial word"""
        word_counter = Counter(self.word.lower())
        return [choice for choice in choices if choice != self.word and
            Counter(choice.lower()) == word_counter]
