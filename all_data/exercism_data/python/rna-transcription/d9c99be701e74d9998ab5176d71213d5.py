class DNA:
    def __init__(self, s):
        self.s = s
        self.d = {'A':'U', 'T':'A', 'G':'C', 'C':'G'}
    def to_rna(self):
        return ''.join(self.d[c] for c in self.s)
