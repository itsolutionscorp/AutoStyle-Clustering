#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Exercism exercise #2: rna-transcription

Code for the second exercism exercise.

Written using Python 3.4
"""


def rna_nucleotide(dna_nucleotide):
    """Given a DNA nucleotide return the RNA complement

    The RNA complement for each DNA nucleotide is as follows:

    * `G` -> `C`
    * `C` -> `G`
    * `T` -> `A`
    * `A` -> `U`

    Args:
        dna_nucleotide: A single character string with the given DNA
            nucleotide, which must be either A, C, G, or T.

    Returns:
        A single character string with the RNA complement or a question mark if
        the given DNA nucleotide was bad.

    Raises:
        N/A
    """
    rna_complement = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U',
    }
    return rna_complement.get(dna_nucleotide, '?')


def to_rna(dna_strand):
    """Givena DNA strand return the RNA complement

    Args:
        dna_strand: A string containing the given DNA strand

    Returns:
        A string containing the RNA complement of the given DNA strand.

    Raises:
        N/A
    """
    rna_complement = ''
    for dna_nucleotide in dna_strand:
        rna_complement += rna_nucleotide(dna_nucleotide)
    return rna_complement
