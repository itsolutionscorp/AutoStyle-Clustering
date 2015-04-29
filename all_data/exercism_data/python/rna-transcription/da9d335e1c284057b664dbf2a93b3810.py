m = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

class DNA:

    def __init__(self, strand):
        self.strand = strand

    def to_rna(self):
        res = ''
        for ntide in self.strand:
            res += m[ntide]
        return res
