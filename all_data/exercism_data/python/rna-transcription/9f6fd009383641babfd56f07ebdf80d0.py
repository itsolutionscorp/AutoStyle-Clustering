from string import maketrans

class DNA(object):
    rna_trans = maketrans("ACGT", "ACGU")

    def __init__(self, strand):
        self.strand = strand

    def to_rna(self):
        return self.strand.translate(self.rna_trans)
