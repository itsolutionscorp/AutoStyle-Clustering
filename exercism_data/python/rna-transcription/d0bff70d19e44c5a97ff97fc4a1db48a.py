class DNA(object):
    THYMIDINE = 'T'
    URACIL = 'U'

    def __init__(self, dna_strand):
        self.dna_strand = dna_strand

    def to_rna(self):
        return self.dna_strand.replace(self.THYMIDINE, self.URACIL)
