from collections import Counter


def word_count(sentence):
    words = map(str.strip, sentence.split())
    c = Counter(words)
    return dict(c)
