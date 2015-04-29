#-*- coding: utf-8 -*-

class DNA:

    def __init__(self, dna):
        self.dna = dna
        self.values = {'G': 'C', 'C': 'G', 'T': 'A', 'A':'U'}

    def to_rna(self):
        res = []
        for nucleotide in self.dna:
            res.append(self.values.get(nucleotide))
        return ''.join(res)
