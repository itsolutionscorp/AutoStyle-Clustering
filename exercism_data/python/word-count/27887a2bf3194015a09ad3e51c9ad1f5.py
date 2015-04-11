import re
import string

class Phrase(object):
    def __init__(self, text):
        self.text = text

    def word_count(self):
        return reduce(self.increment, self.words(), {})

    def words(self):
        return [word.lower() for word in self.normalized().split()]

    def increment(self, counts, word):
        counts[word] = counts.get(word, 0) + 1
        return counts

    def normalized(self):
        return self.text.translate(None, string.punctuation)
