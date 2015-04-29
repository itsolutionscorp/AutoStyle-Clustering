# A translation table from DNA to RNA
dna_to_rna = str.maketrans('GCTA', 'CGAU')

class DNA:
    def __init__(self, code):
        self.code = code

    def to_rna(self):
        return self.code.translate(dna_to_rna)
