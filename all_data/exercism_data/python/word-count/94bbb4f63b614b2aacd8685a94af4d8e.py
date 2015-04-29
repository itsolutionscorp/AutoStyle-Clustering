from collections import defaultdict


def word_count(sentence):
    words = sentence.split()
    d = defaultdict(int)
    for i in words:
        d[i] += 1
    return d
