# -*- coding: utf-8 -*-
# By Nico Gevers (@gevious)


def to_rna(sequence):
    transpose = {
        'G': 'C',
        'C': 'G',
        'A': 'U',
        'T': 'A'
    }
    # Will raise an exception if an unknown character is found
    return "".join(transpose[x] for x in sequence)
