from __future__ import unicode_literals

import string

class DNA(object):
    RNA_TRANS = string.maketrans('GCTA', 'GCUA')

    def __init__(self, dna):
        self.dna = dna

    def to_rna(self):
        return self.dna.translate(self.RNA_TRANS)
