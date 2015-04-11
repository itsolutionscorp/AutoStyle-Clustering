class DNA(object):
    def __init__(self, strand):
        self.strand = strand

    def to_rna(self):
        conversion = { 'G':'C', 'C':'G', 'T':'A', 'A':'U'}
        return ''.join([conversion[i] for i in self.strand])
