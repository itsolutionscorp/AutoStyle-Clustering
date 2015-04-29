'''
:Author: rodrigo
:Date: 12/09/2013
'''


from collections import Counter
from re import split

class Phrase(object):

    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        return Counter(self._split_phrase())

    def _split_phrase(self):
        for word in [_word for _word in split(r'\W+', self._normalize_phrase())
                        if _word]:
            yield word

    def _normalize_phrase(self):
        return self.phrase.lower()
