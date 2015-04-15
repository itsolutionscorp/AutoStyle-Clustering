# -*- coding: utf-8 -*-

import itertools

def hamming(seqA, seqB):
    """
    hamming(iterable, iterable) -> int

    Given two sequences, return the hamming distance.
    Is is assumed that no match occurs once either of the sequences is
    exeeded.
    """

    return sum([1 for a, b in itertools.izip_longest(seqA, seqB)
                if a != b])
