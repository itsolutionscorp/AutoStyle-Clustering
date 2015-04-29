# -*- coding: utf-8 -*-

from __future__ import unicode_literals
from collections import Counter

import string

def word_count(phrase):
    cleaned_phrase = phrase.translate(None, string.punctuation).lower()
    words = cleaned_phrase.split()

    return Counter(words)
