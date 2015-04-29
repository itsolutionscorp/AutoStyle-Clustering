"""DNA

This module contains methods for manipulating textual representations
of DNA sequences.
"""

def to_rna(dna_seq):
    """Return the RNA complement of a given DNA string"""
    rna = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    return "".join([rna[nucleotide] for nucleotide in dna_seq])
