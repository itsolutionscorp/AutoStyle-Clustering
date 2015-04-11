__author__ = 'angelo'
from string import maketrans

trans = maketrans("GCTA", "CGAU")

class DNA():

    def __init__(self, dna_string):
        self.dna_string = dna_string

    def to_rna(self):
        return self.dna_string.translate(trans)
