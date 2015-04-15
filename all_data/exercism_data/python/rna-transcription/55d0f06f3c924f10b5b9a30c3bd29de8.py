__author__ = 'Liam'

# noinspection PyUnresolvedReferences

import string

def to_rna(dnastring):
    result = dnastring.translate(bytes.maketrans(b"GCTA", b"CGAU"))
    return result

