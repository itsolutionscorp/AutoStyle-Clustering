# -*- coding: utf-8 -*-
from collections import Counter


class DNA(object):
    defaults = dict(
        dna=dict(G=0, T=0, C=0, A=0),
        rna=dict(G=0, U=0, C=0, A=0),
    )

    def __init__(self, sequence):
        # TODO validation if sequence is valid, but no tests requires that
        self.sequence = list(sequence.upper())

    def is_valid_nucleotide(self, nucleotide):
        for sequence_type, sequence_default in self.defaults.items():
            if nucleotide in sequence_default.keys():
                return True
        return False

    def count(self, nucleotide):
        if not self.is_valid_nucleotide(nucleotide):
            raise ValueError('{} is not a nucleotide.'.format(nucleotide))
        c = self.nucleotide_counts()
        return c.get(nucleotide.upper(), 0)

    def nucleotide_counts(self):
        c = Counter(self.sequence)
        c.update(self.defaults.get('dna'))
        return c
