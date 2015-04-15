import string
class DNA:
    def __init__(self, dna):
        self.dna = dna
        self.tr = string.maketrans('GCTA', 'CGAU')

    def to_rna(self):
        return string.translate(self.dna, self.tr)
