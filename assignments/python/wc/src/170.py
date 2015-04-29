__author__ = "CucaSF"
import re
def word_count(string):
    words = re.sub(r'[^\w]', ' ', string).lower().split()
    counts = {}
    for word in words:
        if word in counts:
            counts[word] += 1
        else:
            counts[word] = 1
    return counts
