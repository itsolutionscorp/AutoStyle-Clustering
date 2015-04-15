from string import maketrans

dna_to_rna_trantab = maketrans("GCTA", "CGAU")

class DNA:
    def __init__(self, dna):
        self.dna = dna

    def to_rna(self):
        return self.dna.translate(dna_to_rna_trantab)
