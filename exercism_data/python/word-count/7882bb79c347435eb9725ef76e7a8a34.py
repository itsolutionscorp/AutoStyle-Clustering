from itertools import groupby
from collections import defaultdict

def word_count(line):
    words = defaultdict((lambda : 0))
    for k,g in groupby([word.strip() for word in line.replace('\n', ' ').split(' ') if len(word.strip())>0]):
        words[k] = words[k] + len(list(g))
    return dict(words)
