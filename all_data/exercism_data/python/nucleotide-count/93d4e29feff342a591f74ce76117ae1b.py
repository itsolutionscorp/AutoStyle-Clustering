#!/usr/bin/env python

VALID_NUCLEOTIDES = ('A', 'T', 'C', 'G', 'U')
DNA_NUCLEOTIDES = ('A', 'T', 'C', 'G')

class DNA:
    def __init__(self, sequence):
        self.sequence = sequence
    
    def count(self, nucleotide):
        if nucleotide not in VALID_NUCLEOTIDES:
            raise ValueError("{0} is not a nucleotide.".format(nucleotide))
        counter = 0
        for n in self.sequence:
            if n == nucleotide:
                counter += 1
        return counter
    
    def nucleotide_counts(self):
        return {n:self.count(n) for n in DNA_NUCLEOTIDES}
