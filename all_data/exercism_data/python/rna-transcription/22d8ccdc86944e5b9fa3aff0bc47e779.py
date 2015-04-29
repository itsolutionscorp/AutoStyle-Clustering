#!/usr/bin/env python


def to_rna(dna):
    conversion_dict = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    rna = ''

    for nucleotide in dna:
        rna += conversion_dict[nucleotide]

    return rna
