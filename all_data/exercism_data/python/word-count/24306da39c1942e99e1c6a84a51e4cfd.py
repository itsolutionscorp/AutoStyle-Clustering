import collections
import string

class Phrase:
    def __init__(self, phrase):
        self.counts = collections.Counter(
            Phrase.normalize(phrase).split()
        )
        return

    def word_count(self):
        return self.counts

    @staticmethod
    def normalize(phrase):
        '''Lower-case everything and remove punctuation.'''
        return phrase.lower().translate(None, string.punctuation)

    pass
