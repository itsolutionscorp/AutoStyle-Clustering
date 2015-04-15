class DNA:
    def __init__(self, dna):
        pass
        self.dna = dna

    def to_rna(self):
        return self.dna.translate(str.maketrans("GCTA", "CGAU"))
