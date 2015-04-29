#!/usr/bin/python

def hamming(seq_1, seq_2):
    hamm_diff = x = 0
    greater = len(seq_1)
    if len(seq_2) > greater:
        greater = len(seq_2) 
    while x < greater:
        try:
            if seq_1[x] != seq_2[x]:
                hamm_diff += 1
        except IndexError:
            hamm_diff += 1
        x += 1
    return hamm_diff
