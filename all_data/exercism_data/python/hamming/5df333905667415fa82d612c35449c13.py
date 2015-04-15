"""The Hamming distance."""

from itertools import izip_longest


def hamming(string1, string2):
    """Return the number of positions where the symbols are different."""
    return sum(
        sym1 != sym2
        for sym1, sym2 in izip_longest(string1, string2, fillvalue=" ")
    )
