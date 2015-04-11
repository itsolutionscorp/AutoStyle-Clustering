#!/usr/bin/env python

import re
from collections import Counter


class Phrase (object):
    def __init__(self, text):
        self.txt = text

    def word_count(self):
        counter = Counter()
        for m in re.finditer(r'\w+', self.txt):
            w = m.group(0).lower()
            counter[w] += 1
        return counter
