import re
from collections import Counter
class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase

    def get_words(self):
        return re.findall('\w+', self.phrase)

    def word_count(self):
        words = [word.lower() for word in self.get_words()]
        counts = Counter(words)
        return counts
