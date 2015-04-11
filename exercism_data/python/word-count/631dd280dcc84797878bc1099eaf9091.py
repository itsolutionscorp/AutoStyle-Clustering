from collections import defaultdict


def word_count(s):
    words = defaultdict(int)

    for word in s.split():
        words[word] += 1

    return words
