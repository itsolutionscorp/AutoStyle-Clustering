import string

class DNA(object):
    translationTable = string.maketrans("GCTA", "CGAU")

    def __init__(self, dnaString):
        self.dna = dnaString

    def to_rna(self):
        return self.dna.translate(self.translationTable)
