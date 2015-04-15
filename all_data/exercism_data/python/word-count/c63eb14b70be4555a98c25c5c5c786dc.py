"""Produces a count of words"""

import re

class Phrase(object):
    def __init__(self, *args):
        self.phrase = args[0]

    def word_count(self):
        count = {}
        word_list = [x for x in self.phrase.split()]
        for word in word_list:
            """We need to lowercase, remove special chars, and deal with
               the situation where it leaves a zero length string"""
            word = word.lower()
            word = re.sub('[?,:;!@#$%^&]', '', word)
            if len(word) > 0:
                if word not in count:
                    count[word] = 1
                else:
                    count[word] += 1
        return count
