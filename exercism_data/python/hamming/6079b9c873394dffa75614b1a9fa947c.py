#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Submission file for the python hamming exercise.
#
# v2: Swap index for zip object, which adds simple string shortening.
#     Heads up to @ThomasZumsteg for his solution.
# v1: Using list indexing and list comprehensions


def distance(left, right):
    """
    Return the Hamming distance between to strings.

    Note: Strings are shortened to the shortest length.
    """

    distance = 0

    for left_token, right_token in zip(left, right):
        if left_token != right_token:
            distance += 1

    return distance
