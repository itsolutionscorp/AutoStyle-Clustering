#!/usr/bin/env python3
# -*- coding: utf-8 -*-


import itertools


def hamming(dna1, dna2):
    distance = 0
    for d1, d2 in itertools.zip_longest(dna1, dna2):
        if d1 != d2:
            distance += 1
    return distance
