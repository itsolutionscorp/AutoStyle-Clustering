import re

class Phrase:
    def __init__(self, phrase):
        self.words = self.get_words(phrase.lower())

    def get_words(self, phrase):
        return filter(None, re.sub('[^a-z0-9 ]','',phrase).split(' '))

    def word_count(self):
        dict = {}
        for el in self.words:
            if el not in dict:
                dict[el] = 1
            else:
                dict[el] += 1
        return dict
