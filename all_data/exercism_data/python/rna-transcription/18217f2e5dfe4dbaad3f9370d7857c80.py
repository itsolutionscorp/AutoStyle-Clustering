class DNA:

    def __init__(self, strand):
        self.strand = strand

    def to_rna(self):
        return "".join(map(
            lambda nucleotide:
                nucleotide if nucleotide is not 'T' else 'U',
            self.strand))
