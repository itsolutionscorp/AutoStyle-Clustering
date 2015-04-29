import collections
import re

class Phrase(object):
    def __init__(self, sentence):
        self._sentence = sentence

    def word_count(self):
        """Return number of occurences of each word in the sentence"""
        return collections.Counter(self._words())

    def _words(self):
        """Return array of words in the sentence"""
        word = r'\w+'
        sentence = self._sentence.lower()
        self.__words = re.findall(word, sentence)
        return self.__words
