# -*- coding: utf-8 -*-

import itertools
import operator

def hamming(seqA, seqB):
    """
    hamming(iterable, iterable) -> int

    Given two sequences, return the hamming distance.
    Is is assumed that no match occurs once either of the sequences is
    exeeded.
    """

    return (sum(itertools.imap(operator.ne, seqA, seqB))
            + abs(len(seqA) - len(seqB))) ##account for overlap
