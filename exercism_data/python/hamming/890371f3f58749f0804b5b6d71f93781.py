# -*- coding: utf-8 -*-


def hamming(first, second):
    # Complete a DNA strand to a given length with X at the right.
    complete = lambda s, l: s + ('X' * (l - len(s)))

    # Make the two strands make the same length befor counting.
    if len(second) > len(first):
        return hamming(complete(first, len(second)), second)

    elif len(first) > len(second):
        return hamming(first, complete(second, len(first)))

    # Perform the count
    count = 0

    for i in xrange(0, len(first)):
        if first[i] != second[i]:
            count += 1

    return count
