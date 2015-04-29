#!/usr/bin/env python3


def hamming(dna1, dna2):
    extra = abs(len(dna1) - len(dna2))
    count = 0
    for d1, d2 in zip(dna1, dna2):
        if d1 != d2:
            count += 1
    return count + extra
