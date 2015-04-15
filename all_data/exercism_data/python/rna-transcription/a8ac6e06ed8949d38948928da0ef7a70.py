# -*- coding: utf-8 -*-

import itertools
import operator
import functools

def to_rna(sequence):
    """
    to_rna(iterable) -> str

    Return the RNA complement of the given sequence.
    """

    tr = {'T' : 'A',
          'A' : 'U',
          'G' : 'C',
          'C' : 'G'}

    translation_function = functools.partial(operator.getitem, tr)

    return "".join(itertools.imap(translation_function, sequence))
