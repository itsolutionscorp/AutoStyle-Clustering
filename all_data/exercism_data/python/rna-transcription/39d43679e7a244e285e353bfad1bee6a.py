#!/usr/bin/python3

DNA_RNA = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U',
}


def to_rna(sequence):
    """
    Translates DNA sequence to RNA sequence. Accepts and returns string.
    """
    return ''.join(DNA_RNA[nucl] for nucl in sequence.upper())
