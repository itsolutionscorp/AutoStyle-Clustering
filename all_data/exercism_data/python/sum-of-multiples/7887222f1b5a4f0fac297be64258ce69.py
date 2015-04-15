#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Submission file for the python sum-of-multiples exercise.
#
# v1: Use lists of multiples, and set for duplicate removal.


class SumOfMultiples(object):
    def to(self, n):
        multiples = []

        for base in self._base:
            multiples += [x for x in range(base, n, base)]

        return sum(set(multiples))

    def __init__(self, *base):
        self._base = list(base) or [3, 5]
