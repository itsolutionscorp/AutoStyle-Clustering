#!/usr/bin/env python3

try:
    from string import maketrans, translate
except ImportError:
    maketrans = str.maketrans
    translate = str.translate


TO_RNA = maketrans('GCTA', 'GCUA')


class DNA(str):
    def to_rna(self):
        return translate(self, TO_RNA)
