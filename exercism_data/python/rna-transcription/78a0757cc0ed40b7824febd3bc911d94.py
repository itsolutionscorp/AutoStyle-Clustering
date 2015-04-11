# -*- coding: utf-8 -*-

import string

translation_table = string.maketrans('GCTA', 'CGAU')

def to_rna(sequence):
    """
    to_rna(unicode str) -> str

    Return the RNA complement of the given sequence.
    """

    return sequence.translate(translation_table)
