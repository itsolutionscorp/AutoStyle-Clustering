#!/usr/bin/env python
# -*- coding: utf-8 -*-

def distance(dna1, dna2):
    hamming_distance = 0

    for n1, n2 in zip(dna1, dna2):
        if n1 != n2: hamming_distance += 1

    return hamming_distance
