#!/usr/bin/env python

def hamming(seq1, seq2):
    if seq1 == seq2:
        return 0
    if len(seq1) <= len(seq2):
        diff = calc_diff(seq1, seq2) + (len(seq2) - len(seq1))
    else:
        diff = calc_diff(seq2, seq1) + (len(seq1) - len(seq2))
    return diff

def calc_diff(a,b):
    diff = 0
    for i in range(len(a)):
        if a[i] != b[i]:
            diff = diff +1
    return diff
