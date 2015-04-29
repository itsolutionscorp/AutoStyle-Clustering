class DNA(object):
    def __init__(self, dna_strand):
        self.dna_strand = dna_strand
        pass

    def to_rna(self):
        return self.dna_strand.replace('T','U')
