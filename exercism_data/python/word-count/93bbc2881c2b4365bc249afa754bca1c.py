import re
from collections import Counter

class Phrase(object):
    word_pattern = re.compile("[a-zA-Z0-9]+")

    def __init__(self, text):
        words = [m.group(0).lower() for m in self.word_pattern.finditer(text)]
        self.count = Counter(words)

    def word_count(self):
        return self.count
