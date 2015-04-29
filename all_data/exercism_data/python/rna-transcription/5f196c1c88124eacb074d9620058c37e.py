class DNA(object):
    def __init__(self, dna):
        self.dna = dna

    def to_rna(self):
        return ''.join( ('U' if nuc == 'T' else nuc) for nuc in self.dna)
