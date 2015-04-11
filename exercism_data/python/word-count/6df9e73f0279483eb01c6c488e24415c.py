#!/usr/bin/env python

import re


class Phrase:
    def __init__(self, text):
        self.txt = text

    def word_count(self):
        word = {}
        for m in re.finditer(r'\w+', self.txt):
            w = m.group(0).lower()
            if w in word:
                word[w] += 1
            else:
                word[w] = 1
        return word
