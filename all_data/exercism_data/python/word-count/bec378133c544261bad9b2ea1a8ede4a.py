import re
from collections import Counter

class Phrase:
    def __init__(self, phrase):
        self.phrase = phrase

    def get_words(self):
        words = re.findall('\w+', self.phrase)
        return words

    def word_count(self):
        words = [word.lower() for word in self.get_words()]
        counts = Counter(words)
        return dict(counts)
