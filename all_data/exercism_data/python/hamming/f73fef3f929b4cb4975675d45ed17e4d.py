# -*- coding: utf-8 -*-

def hamming(strand_a, strand_b):
    try:
        if strand_a[0]==strand_b[0]:
            return hamming(strand_a[1:], strand_b[1:])
        elif not strand_a[0]==strand_b[0]:
            return hamming(strand_a[1:], strand_b[1:]) + 1
    except Exception:
        return len(strand_a) + len(strand_b)
