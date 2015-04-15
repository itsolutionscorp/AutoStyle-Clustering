from collections import Counter


def word_count(sentence):
    return dict(Counter(sentence.split()))
