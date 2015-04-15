__author__ = 'jos'
import string
from collections import defaultdict


class Phrase():
    def __init__(self, phrase):
        """
        Create a Phrase instance with the passed in phrase
        :param phrase:
        """
        assert isinstance(phrase, basestring)
        self.phrase = phrase

    def word_count(self):
        """
        Takes the phrase and counts all words while removing punctuation and being case insensitive
        :return: dictionary word -> count
        """
        #remove punctuation and map to lowercase
        phrase_bare = self.phrase.translate(string.maketrans(string.uppercase, string.lowercase), string.punctuation)

        #dictionary of word -> count, with a default value of 0 for non-existing keys
        word_count = defaultdict(int)

        for word in phrase_bare.split():
            word_count[word] += 1

        return word_count
