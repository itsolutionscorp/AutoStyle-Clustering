# -*- coding: utf-8 -*-

from string import maketrans

class DNA:
    def __init__(self, dna):
        self.dna = dna
        self.trans_table = maketrans('T', 'U')

    def to_rna(self):
        return self.dna.translate(self.trans_table)
