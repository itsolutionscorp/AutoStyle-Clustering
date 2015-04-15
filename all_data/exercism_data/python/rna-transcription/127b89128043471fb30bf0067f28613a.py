class DNA:
    transcriptions = { 'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U' }

    def __init__(self, strand):
        self.strand = strand

    def to_rna(self):
        return "".join([self.transcriptions[x] for x in self.strand])
