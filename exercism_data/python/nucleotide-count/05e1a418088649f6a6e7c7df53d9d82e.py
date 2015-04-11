#! /usr/bin/env python

dna_nuc = set(['A', 'T', 'C', 'G'])
rna_nuc = set(['A', 'C', 'G', 'U'])


class DNA(object):

    def __init__(self, strand):
        self.strand = strand

    def count(self, nuc):
        """(str) -> dict"""
        if not nuc in rna_nuc.union(dna_nuc):
            raise ValueError("%c is not a nucleotide." % nuc)
        return self.strand.count(nuc)

    def nucleotide_counts(self):
        return {n: self.strand.count(n) for n in dna_nuc}
