#!/usr/bin/env python

def hamming(seq1, seq2):
    return sum(1 for a, b in map(None, seq1, seq2) if a != b)
