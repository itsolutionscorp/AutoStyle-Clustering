#!/usr/bin/python

def hamming(seq1, seq2):
    hamm_diff = 0
    for base1, base2 in zip(seq1, seq2):
        if base1 != base2:
            hamm_diff += 1
    hamm_diff += abs(len(seq1) - len(seq2))
    return hamm_diff
