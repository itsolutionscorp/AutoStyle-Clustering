# -*- coding: utf-8 -*-
"""Implements the rules in the README.md"""
def distance(strand1, strand2):
    """Compares two strands to find their Hamming difference"""
    discrepancies = 0
    """Implements RNA transcription code"""
    for nuc1, nuc2 in zip(strand1, strand2):
        discrepancies += nuc1 != nuc2
    return discrepancies
