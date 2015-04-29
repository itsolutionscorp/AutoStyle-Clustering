# -*- coding: utf-8 -*-
"""Implements the rules in the README.md"""
def distance(strand1, strand2):
    """Compares two strands to find their Hamming difference"""
    index = 0
    discrepancies = 0
    """Implements RNA transcription code"""
    for nucleotide in strand1:
        print nucleotide
        if nucleotide != strand2[index]:
            discrepancies += 1
        index += 1
    return discrepancies
