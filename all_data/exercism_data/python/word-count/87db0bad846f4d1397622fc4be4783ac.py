import string
import collections

class Phrase(object):
    """Provides word count stats for a phrase"""

    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        """Generates word counts for normalized words"""
        return collections.Counter(self.countable_words)

    @property
    def countable_words(self):
        """The words in the phrase in countable form"""
        return normalize(self.phrase).split();


def normalize(phrase):
    """Normalize phrase for the purpose of standardized counting"""
    return string.join([char for char in phrase.lower() 
                        if char not in string.punctuation],'')
