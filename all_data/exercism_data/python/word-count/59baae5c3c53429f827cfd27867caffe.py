import string
from collections import Counter


def word_count(phrase):
    word_counter = Counter()
    # Segment words after stripping punctuation
    for word in phrase.translate(None, string.punctuation).split():
        word_counter[word.lower()] += 1
    return word_counter
