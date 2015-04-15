class DNA(object):
    def __init__(self, dna_string):
        self.dna = dna_string

    def to_rna(self):
        return self.dna.replace('T', 'U')
