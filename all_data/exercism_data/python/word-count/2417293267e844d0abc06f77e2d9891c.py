# -*- coding: utf-8 -*-
import string
from collections import Counter


class Phrase(object):
    TRANSTABLE = string.maketrans(string.uppercase, string.lowercase)
    
    def __init__(self, text):
        self.text = text

    @property
    def prepared_text(self):
        text = string.translate(self.text, self.TRANSTABLE, string.punctuation)
        return filter(None, text.split())

    def word_count(self):
        return dict(Counter(self.prepared_text))
