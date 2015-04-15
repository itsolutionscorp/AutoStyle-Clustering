'''
:Author: rodrigo
:Date: 12/09/2013
'''


from collections import Counter
from re import findall


class Phrase(object):

    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        return Counter(self._split_phrase())

    def _split_phrase(self):
        return findall(r'\w+', self._normalize_phrase())

    def _normalize_phrase(self):
        return self.phrase.lower()
