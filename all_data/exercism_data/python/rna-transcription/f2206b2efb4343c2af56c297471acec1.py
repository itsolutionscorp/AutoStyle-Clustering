# -*- coding: utf-8 -*-


def to_rna(sequence):
    """
    to_rna(iterable) -> str

    Return the RNA complement of the given sequence.
    """

    tr = {'T' : 'A',
          'A' : 'U',
          'G' : 'C',
          'C' : 'G'}

    return "".join([tr[c] for c in sequence])
