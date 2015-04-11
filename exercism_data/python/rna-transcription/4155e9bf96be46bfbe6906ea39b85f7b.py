from string import maketrans

class DNA(object):
    def __init__(self, strand):
        self.strand = strand

    def to_rna(self):
        tr = maketrans("GCTA", "CGAU");
        return self.strand.translate(tr)
