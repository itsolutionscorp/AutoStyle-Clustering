# -*- coding: utf-8 -*-

from string import maketrans as mt

translation = mt('GCTA', 'CGAU')


def to_rna(DNAstrand):
    return DNAstrand.translate(translation)
