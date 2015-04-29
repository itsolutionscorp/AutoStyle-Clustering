import string
import collections

class Phrase(object):

    def __init__(self, text):
        self.text = text

    def word_count(self):
        return collections.Counter(self.words())

    def words(self):
        return self.text.translate(None, string.punctuation).lower().split()
