class DNA:
    def __init__(self, string):
        self.dna = string

    def to_rna(self):
        return self.dna.replace('T', 'U')
