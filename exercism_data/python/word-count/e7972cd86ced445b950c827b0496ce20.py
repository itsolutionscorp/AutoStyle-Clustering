import string
import collections

class Phrase(object):
    """Provides word count stats for a phrase"""

    def __init__(self, phrase):
        """Sets the phrase"""
        self.phrase = phrase

    def word_count(self):
        """Generates word counts for normalized words"""
        return collections.Counter(self.countable_words)

    @property
    def countable_words(self):
        """the words in the phrase in countable form"""
        base_words = self.phrase.split();
        normalized_words = [normalize_word(w) for w in base_words]
        non_empty_words = filter(None, normalized_words)
        return non_empty_words


def normalize_word(word):
    """Normalize word for the purpose of standardized counting"""
    return string.join([char for char in word.lower() 
                        if char not in string.punctuation],'')
