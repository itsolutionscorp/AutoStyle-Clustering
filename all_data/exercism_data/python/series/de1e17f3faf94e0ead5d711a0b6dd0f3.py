#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Submission file for the python series exercise.
#
# v1: Using list indexing and list comprehensions


def slices(digits, count):
    """
    Return a list of ``count``-length slices from an integer iterable.
    """

    last = len(digits)-count+1

    if count == 0:
        raise ValueError('Zero-length slices are not allowed.')

    elif last <= 0:
        raise ValueError('Slice is longer than the string itself.')

    else:
        series = []

        for start in range(last):
            substring = digits[start:start+count]
            series.append([int(x) for x in substring])

        return series
