import itertools


def word_count(phrase):
    """TODO: Docstring for word_count.

    :phrase: TODO
    :returns: TODO

    """
    return {
        word: len(list(occurrences)) for word, occurrences in
        itertools.groupby(sorted(phrase.strip().split()))
    }
