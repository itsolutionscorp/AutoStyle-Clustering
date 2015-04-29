# -*- coding: utf-8 -*-

_translation_map = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
}

def to_rna(strand):
    try:
        return "".join(_translation_map[n] for n in strand)
    except KeyError:
        raise ValueError("invalid nucleotide in '{}'".format(strand))
