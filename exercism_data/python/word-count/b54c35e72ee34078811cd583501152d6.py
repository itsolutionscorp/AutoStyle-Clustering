from collections import defaultdict


def word_count(phrase):
    word_freq = defaultdict(int)
    for word in phrase.split():
        word_freq[word] += 1
    return word_freq
