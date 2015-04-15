class DNA(object):
    def __init__(self,strand):
        self.strand = strand

    def to_rna(self):
        tr = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
        rna = ''
        for strand in self.strand:
            rna += tr[strand]
        return rna
