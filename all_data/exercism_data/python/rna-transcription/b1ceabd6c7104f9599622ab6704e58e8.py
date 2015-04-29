#!/usr/bin/env python3

from string import maketrans, translate


TO_RNA = maketrans('GCTA', 'GCUA')


class DNA(str):
    def to_rna(self):
        return translate(self, TO_RNA)
