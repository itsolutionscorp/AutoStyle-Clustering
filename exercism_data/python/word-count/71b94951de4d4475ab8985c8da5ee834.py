# -*- coding: utf-8 -*-

from __future__ import unicode_literals
from collections import Counter
import re

def word_count(phrase):
    regex = re.compile(r'[!@£$%^&*:,]')
    words = regex.sub('', phrase)
    words = words.lower()
    words = words.split()

    cnt = Counter()
    for word in words:
        cnt[word] += 1

    return cnt
