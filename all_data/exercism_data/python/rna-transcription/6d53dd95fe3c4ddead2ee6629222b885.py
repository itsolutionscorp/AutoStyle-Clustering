class DNA:
    THYMINE = 'T'
    URACIL  = 'U'

    def __init__(self, dna):
        self.dna = dna

    def to_rna(self):
        return self.dna.replace(self.THYMINE, self.URACIL)
