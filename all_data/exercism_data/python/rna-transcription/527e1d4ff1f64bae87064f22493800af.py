#!/usr/bin/python


class DNA(object):
    def __init__(self, strand):
        self.strand = strand
        self.struct = {'A':'A', 'G':'G', 'C':'C','T':'U'}

    def to_rna(self):
        rna = [self.struct[x] for x in self.strand]
        return ''.join(rna)
