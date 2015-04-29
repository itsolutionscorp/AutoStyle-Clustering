import collections


def word_count(word):

    # Split string into words
    word = word.split()

    # Count the words
    return dict(collections.Counter(word))
