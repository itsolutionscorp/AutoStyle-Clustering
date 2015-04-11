from collections import Counter


class Phrase(object):

    def __init__(self, word_string):
        words = [self.clean_word(word) for word in word_string.split(' ')]
        self.counter = Counter((word for word in words if word))

    def word_count(self):
        return dict(self.counter)

    def clean_word(self, word):
        return ''.join([c.lower() for c in word if c.isalnum()])
