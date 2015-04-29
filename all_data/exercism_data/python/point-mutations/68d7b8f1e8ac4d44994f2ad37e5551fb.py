#!/usr/bin/python3


def hamming_distance(seq1, seq2):
    return sum(1 for nuc1, nuc2 in zip(seq1, seq2) if nuc1 != nuc2)
