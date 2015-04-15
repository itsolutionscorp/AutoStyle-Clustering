import re
from collections import Counter


class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        return dict(Counter(
            w.lower() for w in re.sub(r'[^a-zA-Z0-9 ]', '', self.phrase).split()
        ))
