import string

class DNA:
    def __init__(self, dna):
        self.dna = dna

    def to_rna(self):
        translate_table = string.maketrans('GCTA', 'CGAU')
        return self.dna.translate(translate_table)
