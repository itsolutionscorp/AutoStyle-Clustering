class DNA(object):
    def __init__(self, strand):
        self.strand = strand

    def to_rna(self):
        return ''.join(map(self.nucleotide_dna_to_rna, self.strand))

    @staticmethod
    def nucleotide_dna_to_rna(nucleotide):
        return 'U' if nucleotide == 'T' else nucleotide
