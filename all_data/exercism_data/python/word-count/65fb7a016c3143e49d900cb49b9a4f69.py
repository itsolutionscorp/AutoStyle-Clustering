from __future__ import unicode_literals

import re
from collections import Counter

class Phrase(object):
    def __init__(self, phrase):
        phrase = phrase.lower()
        self.words = Counter(re.findall(r'\w+', phrase))

    def word_count(self):
        return dict(self.words)
