from collections import defaultdict


def word_count(phrase):
    word_count = defaultdict(lambda: 0)

    for word in phrase.split():
        word_count[word] += 1

    return word_count
