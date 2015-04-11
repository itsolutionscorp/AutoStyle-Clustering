#!/usr/bin/env python
# coding=utf-8
"""
Testing stuff.
"""


def to_rna(dna):
    """
    Convert dna to rna
    @param dna: str
    @return: str
    """
    dna = dna.upper()
    conversion_dict = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    rna = ''

    for letter in dna:
        rna += conversion_dict[letter]
    return rna


if __name__ == '__main__':
    pass
