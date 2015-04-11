class DNA:
    def __init__(self, dna):
        self.rna = dna.replace('T', 'U')

    def to_rna(self):
        return self.rna
