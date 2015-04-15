import dna

from string import maketrans

class DNA(object):
    def __init__(self, dna_string):
        self.dna_string = dna_string

    def to_rna(self):
        thymidine = "T"
        uracil = "U"
        dna_to_rna = maketrans(thymidine, uracil)
        return self.dna_string.translate(dna_to_rna)
