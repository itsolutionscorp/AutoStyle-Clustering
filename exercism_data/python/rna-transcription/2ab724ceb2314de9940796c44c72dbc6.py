#!/usr/bin/env python3

translation = str.maketrans(
    'GCTA',
    'CGAU')


def to_rna(s):
    return s.translate(translation)
