# -*- coding: utf-8 -*-

import operator
import itertools

def slices(sequence, length):
    """
    slices(str, int) -> list of list of int

    Return a list of consecutive series with given length if each character in
    sequence is in "0123456789".
    """

    if length > len(sequence):
        raise ValueError("length must be lesser or equal to len(sequence)")
    elif length <= 0:
        raise ValueError("length must be greater than 0")

    sequence = [int(a) for a in sequence]

    return [sequence[start : start + length]
            for start in range(len(sequence) - length + 1)]


def largest_product(sequence, length):
    """
    largest_product(str, int) -> int

    Return the biggest product of consecutive ints in the given string.

    Runs in O(len(sequence)).
    """

    if length == 0:
        return 1
    if length > len(sequence):
        raise ValueError("length must be lesser or equal to len(sequence)")

    sequences = [[int(a) for a in sub_sequence]
                 for sub_sequence in sequence.split('0')
                 if len(sub_sequence) >= length]

    best = 0
    for sequence in sequences:
        actual = reduce(operator.mul, sequence[:length], 1)
        best = max(actual, best)

        for old, new in zip(sequence[:-length], sequence[length:]):
            actual *= new
            actual //= old
            best = max(actual, best)

    return best
