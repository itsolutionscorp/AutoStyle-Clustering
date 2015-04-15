#!/usr/bin/env python
"""

"""


def to_rna(string):
    new_string = ''
    # GCTA to CGAU
    dna = ['G', 'C', 'T', 'A']
    rna = ['C', 'G', 'A', 'U']

    for char in string:
        if char in dna:
            new_string += rna[dna.index(char)]  # better than large if /elif block? Definitely easier to modify...
    return new_string
