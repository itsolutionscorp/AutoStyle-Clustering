#!/usr/bin/env python2
# -*- coding: utf-8 -*-

def hamming(seq1, seq2):
    return sum([1 if x[0] != x[1] else 0 for x in zip(seq1, seq2)])+abs(len(seq1)-len(seq2))

if __name__ == '__main__':
    print '%s' % ('This script is meant to be used this way.')
