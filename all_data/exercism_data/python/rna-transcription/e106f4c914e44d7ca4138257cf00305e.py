"""DNA

This module contains methods for manipulating textual representations
of DNA sequences.
"""

def to_rna(dna_seq):
    """Return the RNA complement of a given DNA string"""
    rna = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    rna_seq = []
    for nucleotide in dna_seq:
        rna_seq.append(rna[nucleotide])
    return "".join(rna_seq)
