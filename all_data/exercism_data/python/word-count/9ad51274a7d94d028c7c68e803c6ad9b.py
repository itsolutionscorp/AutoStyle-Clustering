import re
from collections import defaultdict

class Phrase:

    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        words = re.findall('\w+', self.phrase)
        count = defaultdict(int)
        for word in words:
          count[word.lower()] += 1
        return count
