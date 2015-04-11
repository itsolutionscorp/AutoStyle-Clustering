"""The Hamming distance."""


def distance(string1, string2):
    """Return the number of positions where the symbols are different."""
    if len(string1) != len(string2):
        raise ValueError(string1, string2)

    return sum(1 for sym1, sym2 in zip(string1, string2) if sym1 != sym2)
