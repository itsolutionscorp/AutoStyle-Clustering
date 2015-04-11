from collections import Counter
from re import findall


class Phrase:

    def __init__(self, phrase):
        self.phrase = phrase

    def get_words(self):
        """Return the list of lowercased words in the given phrase."""
        return findall(r'\w+', self.phrase.lower())

    def word_count(self):
        """Return a dict with each word in the phrase and its occurences."""
        return Counter(self.get_words())
