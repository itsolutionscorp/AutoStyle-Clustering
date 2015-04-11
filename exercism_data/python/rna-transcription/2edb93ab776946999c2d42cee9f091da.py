class DNA:
    def __init__(self, nucleotide):
        self._nucleotide = nucleotide

    def to_rna(self):
        return self._nucleotide.replace('T', 'U')
