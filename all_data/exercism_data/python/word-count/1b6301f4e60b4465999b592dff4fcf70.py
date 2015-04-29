#using python 3.3
__author__ = "CucaSF"

import re

def word_count(string):
    #delete all symbols in string, transform all in lower and split
    words = re.sub(r'[^\w]', ' ', string).lower().split()
    counts = {}
    for word in words:
        if word in counts:
            counts[word] += 1
        else:
            counts[word] = 1
    return counts
