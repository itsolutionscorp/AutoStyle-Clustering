#! /usr/bin/env python

dna_nuc = set(['A', 'C', 'G', 'T'])
rna_nuc = set(['A', 'C', 'G', 'U'])


class DNA(object):

    def __init__(self, strand):
        self.strand = strand

    def count(self, nuc):
        """(str) -> dict"""
        self._validate(nuc)
        return self.strand.count(nuc)

    def nucleotide_counts(self):
        return {n: self.count(n) for n in dna_nuc}

    def _validate(self, nuc):
        if nuc not in rna_nuc.union(dna_nuc):
            raise ValueError("%c is not a nucleotide." % nuc)
