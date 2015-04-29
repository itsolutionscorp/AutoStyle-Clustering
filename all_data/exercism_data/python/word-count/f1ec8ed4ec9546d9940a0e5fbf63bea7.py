# -*- coding: utf-8 -*-
import string
from collections import Counter

class Phrase(object):
    def __init__(self, text):
        self.text = text

    @property
    def prepared_text(self):
        trans = string.maketrans(string.uppercase, string.lowercase)
        text = string.translate(self.text, trans, string.punctuation)
        return filter(None, text.split())

    def word_count(self):
        return dict(Counter(self.prepared_text))
