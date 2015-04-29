class DNA(object):
    rna_complement = str.maketrans("ATCG", "UAGC")
    def __init__(self, strand):
        self.strand = strand
    def to_rna(self):
        return self.strand.translate(DNA.rna_complement)
