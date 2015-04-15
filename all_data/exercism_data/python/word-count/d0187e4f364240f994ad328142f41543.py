from collections import Counter
import re


class Phrase(object):
    word = re.compile(r'([a-zA-Z0-9]+)')

    def __init__(self, s):
        self.s = s

    def word_count(self):
        dct = Counter()
        for m in Phrase.word.finditer(self.s):
            dct[m.group(1).lower()] += 1
        return dct
