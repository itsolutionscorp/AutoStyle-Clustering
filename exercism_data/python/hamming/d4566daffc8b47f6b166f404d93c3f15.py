#!/usr/bin/env python
# -*- coding: UTF-8 -*-

"""
By counting the number of differences between two homologous DNA strands
taken from different genomes with a common ancestor, we get a measure of
the minimum number of point mutations that could have occurred on the
evolutionary path between the two strands.

This is called the 'Hamming distance'
"""

__version__ = "0.0.3"
__all__ = ["__version__", "distance"]


def distance(strand, other):
    """Calculates the hamming distance between two DNA strands

    .. versionadded:: 0.0.1
    .. versionchanged:: 0.0.3

    :param strand: the strand to compare
    :param other: the strand to compare against
    """
    return sum([int(s != o) for s, o in zip(strand, other)])
