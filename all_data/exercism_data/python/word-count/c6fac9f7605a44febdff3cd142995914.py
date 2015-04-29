from collections import Counter


def word_count(string):
    words = string.split()
    count = Counter(words)
    return dict(count)
