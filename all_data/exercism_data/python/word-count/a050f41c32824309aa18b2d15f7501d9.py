# -*- coding: utf-8 -*-

import re
from collections import Counter

def word_count(s):
    reg = re.compile(r'[^\w\d]+')
    words = [w.lower() for w in reg.split(s) if w]
    return dict(Counter(words))
