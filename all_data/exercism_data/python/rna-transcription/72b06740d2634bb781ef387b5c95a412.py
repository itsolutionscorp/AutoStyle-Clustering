# -*- coding: utf-8 -*-


TRANSLATIONS = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
}


def to_rna(dna):
    return ''.join([TRANSLATIONS[n] for n in dna.upper()])
