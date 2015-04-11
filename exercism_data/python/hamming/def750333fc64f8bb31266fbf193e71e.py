__author__ = 'jimblackler'

from itertools import ifilter, izip


def distance(a, b):
    """Calculates the number of differences between two homologous DNA strands.

    :param a: The first strand.
    :param b: The second strand.
    :return: The number of differences between the two strands.
    """
    return sum(0 if x == y else 1 for x, y in izip(a, b))
