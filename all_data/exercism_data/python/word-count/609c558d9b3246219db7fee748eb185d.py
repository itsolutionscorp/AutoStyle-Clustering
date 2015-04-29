from collections import Counter
import re

class Phrase:
    def __init__(self, input):
        self._word_count = Counter([x.lower() for x in re.split('\W+', input) if x])

    def word_count(self):
        return self._word_count
