# -*- coding: utf-8 -*-

from collections import Counter

class DNA:

    def __init__(self, strand):
        counts = {'A':0, 'C':0, 'G':0, 'T':0}
        counts.update(Counter(strand))
        self.counts = counts

    def count(self, nucleotide):
        if not nucleotide in 'ACGTU':
            raise ValueError("{} is not a nucleotide.".format(nucleotide))
        return self.counts.get(nucleotide, 0)

    def nucleotide_counts(self):
        return self.counts
