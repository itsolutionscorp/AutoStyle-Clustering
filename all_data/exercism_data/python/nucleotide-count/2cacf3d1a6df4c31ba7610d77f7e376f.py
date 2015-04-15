from __future__ import unicode_literals

from collections import Counter

class DNA(object):
    """ Stores and counts nucleotides in DNA sequences """
    DNA_NUCLEOTIDES = {'A', 'C', 'G', 'T'}
    NUCLEOTIDES = DNA_NUCLEOTIDES | {'U'}

    def __init__(self, sequence):
        """ Assumes input sequence is valid """
        self.nucleotides = Counter(sequence.upper())
        for nucleotide in self.DNA_NUCLEOTIDES:
            self.nucleotides.setdefault(nucleotide, 0)

    def nucleotide_counts(self):
        return self.nucleotides

    def count(self, nucleotide):
        """ Only returns counts for valid nucleotides """
        if nucleotide in self.NUCLEOTIDES:
            return self.nucleotides[nucleotide]
        else:
            raise ValueError(nucleotide + " is not a nucleotide.")
