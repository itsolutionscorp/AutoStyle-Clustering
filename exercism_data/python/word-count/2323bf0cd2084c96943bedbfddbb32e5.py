from collections import Counter


def word_count(text):
    text = text.split()
    return Counter(text)
