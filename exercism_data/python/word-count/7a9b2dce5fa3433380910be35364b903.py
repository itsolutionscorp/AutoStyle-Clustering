from collections import defaultdict


def word_count(phrase):
    """
    Count the number of times each word appears in a given phrase.
    """
    _word_count = defaultdict(lambda: 0)

    for word in phrase.split():
        _word_count[word] += 1

    return _word_count
