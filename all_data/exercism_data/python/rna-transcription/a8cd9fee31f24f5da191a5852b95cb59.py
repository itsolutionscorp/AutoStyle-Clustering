#!/usr/bin/env python
# -*- coding: UTF-8 -*-

"""
Utility methods for manipulating DNA
"""

import string

__version__ = '0.0.1'
__all__ = ['__version__', 'to_rna']

_TRANSLATION_TABLE = string.maketrans("GCTA", "CGAU")

def to_rna(strand=''):
    """Transcribe a DNA strand to its corresponding RNA

    * `G` -> `C`
    * `C` -> `G`
    * `T` -> `A`
    * `A` -> `U`

    .. versionadded:: 0.0.1

    :param strand: the dna strand to transcribe
    """
    return strand.translate(_TRANSLATION_TABLE)
