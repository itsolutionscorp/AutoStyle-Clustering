#!/usr/bin/env python
import re

def word_count(text):
    words = {}
    target = text.lower()
    target = re.findall(r'[^_\W]+', target)
    for word in target:
        if word in words:
            words[word] += 1
        else:
            words[word] = 1
    return words
