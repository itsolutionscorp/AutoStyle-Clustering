#!/usr/bin/env python
# coding=utf-8
"""
Testing stuff.
"""


conversion_dict = {'G': 'C',
                   'C': 'G',
                   'T': 'A',
                   'A': 'U'}


def to_rna(dna):
    """
    Convert dna to rna
    @param dna: str
    @return: str
    """
    dna = dna.upper()

    for n in dna:
        if n in conversion_dict.keys():
            dna = dna.replace(n, conversion_dict[n])
    return dna


if __name__ == '__main__':
    print "Testing"
