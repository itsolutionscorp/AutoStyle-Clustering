# -*- coding:utf-8 -*-
from collections import Counter

NUCLEOTIDES = "ACGTU"

def is_nucleotide(symbol):
    return symbol in NUCLEOTIDES

class DNA(object):
    _default = {'A': 0, 'T': 0, 'C': 0, 'G': 0}

    def __init__(self, dna):
        self._dna = dna
        self._counter = None

    def nucleotide_counts(self):
        if self._counter is None:
            counter = Counter(self._dna)
            counter.update(self._default)
            self._counter = counter
        return self._counter

    def count(self,  nucleotide):
        if not is_nucleotide(nucleotide):
            raise ValueError("%s is not a nucleotide." % nucleotide)
        return self.nucleotide_counts().get(nucleotide, 0)
