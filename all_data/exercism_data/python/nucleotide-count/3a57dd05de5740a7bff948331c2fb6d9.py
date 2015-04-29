#!/usr/bin/env python3

from collections import Counter

DNA_NUCLEOTIDES = 'ACGT'
RNA_NUCLEOTIDES = 'ACGU'


class DNA(str):
    def __init__(self, s):
        if not set(s) <= set(DNA_NUCLEOTIDES):
            raise ValueError

    def nucleotide_counts(self):
        c = Counter({n: 0 for n in DNA_NUCLEOTIDES})
        c.update(self)
        return dict(c)

    def count(self, n):
        if n not in DNA_NUCLEOTIDES + RNA_NUCLEOTIDES:
            raise ValueError("{} is not a nucleotide.".format(n))
        return super().count(n)
