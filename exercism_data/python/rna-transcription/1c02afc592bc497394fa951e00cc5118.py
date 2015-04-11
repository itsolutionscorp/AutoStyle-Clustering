class DNA:

    _translation = dict(C='C', G='G', A='A', T='U')

    def __init__(self, dna):
        self.dna = dna

    def to_rna(self):
        rna = ''.join(self._translation[c] for c in self.dna)
        return rna
