import re
from collections import Counter

class Phrase:
    def __init__(self, phrase):
        self.phrase = phrase
        self.words = re.sub(r'[^\w]+', r' ', phrase).lower().split()

    def word_count(self):
        return dict(Counter(self.words))
