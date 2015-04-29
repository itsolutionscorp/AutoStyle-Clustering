#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""Exercism exercise #2: rna-transcription

Written using Python 3.4
"""


def rna_nucleotide_complement(dna_nucleotide):
    """Given a DNA nucleotide return the RNA complement
    """
    rna_complement = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U',
    }
    return rna_complement[dna_nucleotide]


def to_rna(dna_strand):
    """Given a DNA strand return the RNA complement
    """
    rna_complement = ''
    for dna_nucleotide in dna_strand:
        rna_complement += rna_nucleotide_complement(dna_nucleotide)
    return rna_complement
