#!/usr/bin/python

class DNA(object):
    def __init__(self, string):
        self.string = string
        self.nucleotides = ['A', 'T', 'G', 'C']

    def count(self, nucleotide):
        if nucleotide not in self.nucleotides and nucleotide != "U":
            raise ValueError, '{} is not a nucleotide.'.format(nucleotide)
        return self.string.count(nucleotide)

    def nucleotide_counts(self):
        counts = dict()
        for n in self.nucleotides:
            counts[n] = self.count(n)
        return counts
        
