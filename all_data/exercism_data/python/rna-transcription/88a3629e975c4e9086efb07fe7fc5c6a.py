class DNA(object):
    def __init__(self, dna):
        self.dna = dna
        self.rna = dna.replace('T', 'U')

    def to_rna(self):
        return self.rna
