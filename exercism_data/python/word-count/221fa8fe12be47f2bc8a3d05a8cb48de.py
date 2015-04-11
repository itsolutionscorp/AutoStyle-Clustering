from collections import defaultdict


def word_count(string):
    words = string.split()
    counts = defaultdict(int)
    for word in words:
        counts[word] += 1
    return counts
