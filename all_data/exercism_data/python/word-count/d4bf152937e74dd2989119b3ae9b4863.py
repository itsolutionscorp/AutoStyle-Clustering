import re
from collections import defaultdict

def word_count(phrase):
    words = defaultdict(lambda: 0)
    for word in re.split(r"\s", phrase):
        if word != u'':
            words[word] += 1
    return words
