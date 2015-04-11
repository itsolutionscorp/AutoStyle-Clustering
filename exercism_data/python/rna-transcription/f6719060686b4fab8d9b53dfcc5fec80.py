class DNA:
    def __init__(self, dna_strand):
        self._dna_strand = dna_strand

    def to_rna(self):
        return self._dna_strand.replace('T', 'U')
