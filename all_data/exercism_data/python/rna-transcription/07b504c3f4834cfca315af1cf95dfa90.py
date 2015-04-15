# -*- coding: utf-8 -*-

from string import maketrans

translation = maketrans('GCTA', 'CGAU')


def to_rna(DNAstrand):
    return DNAstrand.translate(translation)
