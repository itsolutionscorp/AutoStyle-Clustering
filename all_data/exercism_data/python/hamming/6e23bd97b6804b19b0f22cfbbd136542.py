# -*- coding: utf-8 -*-


def distance(strand1, strand2):
    """
    Calculate the Hamming distance between two DNA strands.
    returns False if strings are not equal length.
    """
    if len(strand1) != len(strand2):
        return False
    return sum([1 for base1, base2 in zip(strand1, strand2) if base1 != base2])
