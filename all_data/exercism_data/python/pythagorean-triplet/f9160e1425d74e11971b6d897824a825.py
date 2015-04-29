"""Pythagorean triplets."""

import itertools
from fractions import gcd


def primitive_triplets(y):
    """Return all primitive Pythagorean triples with a given multiple of 4.

    :param y: a multiple of 4
    """
    if y % 4 != 0:
        raise ValueError("Argument must be divisible by 4")

    triplets = set()
    y_divided_by_2 = y / 2
    for n in xrange(1, y_divided_by_2):
        if y_divided_by_2 % n != 0:
            continue
        m = y_divided_by_2 / n
        if m <= n or (m - n) % 2 == 0 or gcd(m, n) != 1:
            continue
        x = m ** 2 - n ** 2
        z = m ** 2 + n ** 2
        triplets.add(tuple(sorted((x, y, z))))
    return triplets


def triplets_in_range(start, end):
    """Return all Pythagorean triples within bounds."""
    return set(
        (x, y, z)
        for x, y, z in itertools.combinations(xrange(start, end + 1), 3)
        if x ** 2 + y ** 2 == z ** 2
    )


def is_triplet(xyz):
    """Return ture if a Pythagorean triple."""
    x, y, z = sorted(xyz)
    return x ** 2 + y ** 2 == z ** 2
