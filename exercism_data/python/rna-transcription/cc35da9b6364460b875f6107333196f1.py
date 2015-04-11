"""This module contains a function to_rna that finds the RNA
transcription of a given DNA base string."""

def to_rna(dna):
    """Returns the string with DNA bases mapped to
    corresponding RNA bases"""
    return dna.translate(str.maketrans('ATGC','UACG'))
