#!/usr/bin/env python


def distance(dna_string, mutated_string):
    hamming_distance = 0
    for d, m in zip(dna_string, mutated_string):  # iterate over both strings at once
        if d != m:
            hamming_distance += 1
    return hamming_distance
