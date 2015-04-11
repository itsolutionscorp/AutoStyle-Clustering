class DNA:
    THYMIDINE = 'T'
    URACIL    = 'U'

    def __init__(self, strand):
        self.strand = strand

    def to_rna(self):
        return self.strand.replace(self.THYMIDINE, self.URACIL)
