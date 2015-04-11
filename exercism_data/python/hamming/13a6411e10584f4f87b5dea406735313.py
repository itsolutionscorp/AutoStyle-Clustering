#!/usr/bin/env python

def hamming(seq1,seq2):
    hd = abs(len(seq1) - len(seq2))
    for s in zip(seq1, seq2):
        if s[0] != s[1]:
            hd+=1
    return hd
