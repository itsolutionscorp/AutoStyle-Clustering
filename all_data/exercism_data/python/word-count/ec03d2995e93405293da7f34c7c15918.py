#!/usr/bin/env python3
# -*- coding: utf-8 -*-


import collections
import string


_translation = str.maketrans(string.punctuation, " "*len(string.punctuation))
def word_count(s):
    s = s.translate(_translation).lower()
    words = s.split()
    return dict(collections.Counter(words))
