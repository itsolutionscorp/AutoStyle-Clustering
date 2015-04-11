# -*- coding: utf-8 -*-
"""Implements the rules in the README.md"""
def to_rna(dna):
    """Implements RNA transcription code"""
    rna = ""
    for nucleotide in dna:
        if nucleotide == "G":
            rna = rna + "C"
        if nucleotide == "C":
            rna = rna + "G"
        if nucleotide == "T":
            rna = rna + "A"
        if nucleotide == "A":
            rna = rna + "U"
    return rna
