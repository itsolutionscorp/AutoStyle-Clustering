"""For Want of a Nail."""

import itertools


def proverb(words, qualifier=None):
    """Return "For Want of a Nail"."""
    phrases = (
        "For want of a {} the {} was lost.".format(want, lost)
        for want, lost in pairwise(words)
    )

    qualifier = "" if qualifier is None else qualifier + " "
    phrases = itertools.chain(
        phrases,
        ["And all for the want of a {}{}.".format(qualifier, words[0])])

    return "\n".join(phrases)


def pairwise(iterable):
    """s -> (s[0], s[1]), (s[1], s[2]), (s[2], s[3]), ..."""
    iterable1, iterable2 = itertools.tee(iterable)
    next(iterable2, None)
    return itertools.izip(iterable1, iterable2)
