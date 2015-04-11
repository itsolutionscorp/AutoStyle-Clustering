import string
import collections

class Phrase(object):
    """Provides word count stats for a phrase"""

    def __init__(self, phrase):
        """Sets the phrase"""
        self.phrase = phrase

    def word_count(self):
        """Generates word counts for words (lower case, no punctuation)"""

        words = self.phrase.split() 

        normalized_words = [normalize_word(w) for w in words]

        non_empty_words = filter(None, normalized_words)

        return collections.Counter(non_empty_words)


def normalize_word(word):
    """Normalize word for the purpose of standardized counting"""
    return string.join([char for char in word.lower() 
                        if char not in string.punctuation],'')
