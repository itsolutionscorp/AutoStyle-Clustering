'''
:Author: rodrigo
:Date: 12/09/2013
'''

from re import split


class Phrase(object):

    def __init__(self, phrase):

        self.phrase = phrase

    def word_count(self):

        word_count_return = {}
        list_word = self.split_words()

        for word in set(self.split_words()):
            word_count_return[word] = list_word.count(word)

        return word_count_return

    def split_words(self):

        return list(filter(None, split(r'\W*', self.phrase.lower())))
