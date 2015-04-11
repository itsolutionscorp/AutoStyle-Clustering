# -*- coding: utf-8 -*-
"""Implements the rules in the README.md"""
def distance(strand1, strand2):
    """Compares two strands to find their Hamming difference"""
    return sum(nuc1 != nuc2 for nuc1, nuc2 in zip(strand1, strand2))
