from string import maketrans


class DNA:

    def __init__(self, strand):
        self.strand = strand

    def to_rna(self):
        return self.strand.translate(maketrans('GCTA', 'CGAU'))
