"""
hamming

"""

import math


def hamming(s0, s1):
    """
    Determine hamming distance between s0 and s1

    :param s0:
    :param s1:
    :return:
    """
    # We'll get the difference of lengths of s0, s1
    # in case we need to add that to the hamming distance
    length_diff = math.fabs(len(s0) - len(s1))

    # Now, we want to use the smaller length string (if any)
    # so we don't get index errors
    if len(s0) < len(s1):
        return len(
            [True for i, j in enumerate(s0) if s0[i] != s1[i] ]
        ) + length_diff
    else:
        return len(
            [True for i, j in enumerate(s1) if s0[i] != s1[i] ]
        ) + length_diff
