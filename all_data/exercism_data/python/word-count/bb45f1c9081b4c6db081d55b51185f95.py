import re
from collections import Counter

class Phrase:

    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        return dict(Counter(re.findall('\w+', self.phrase.lower())))
