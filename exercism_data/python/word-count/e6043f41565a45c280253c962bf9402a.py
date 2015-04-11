import re
from collections import defaultdict

class Phrase(object):

    def __init__(self, words):
        self.words = words

    def word_count(self):
        word_count = defaultdict(lambda: 0)
        for w in re.finditer('\w+', self.words):
            wkey = w.group().lower()
            word_count[wkey] += 1
        return word_count
