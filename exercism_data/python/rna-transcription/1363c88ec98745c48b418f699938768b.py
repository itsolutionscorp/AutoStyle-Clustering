# -*- coding: utf-8 -*-

from string import maketrans


def to_rna(dna):

    dna_to_rna = maketrans('GCTA', 'CGAU')
    rna = dna.translate(dna_to_rna)

    return rna
