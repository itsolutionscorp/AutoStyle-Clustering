class DNA:
    transcriptions = { 'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U' }

    def __init__(self, strand):
        self.strand = strand

    def to_rna(self):
        return "".join(map(self._translate, self.strand))

    def _translate(self, nucleotide):
        return self.transcriptions[nucleotide]
