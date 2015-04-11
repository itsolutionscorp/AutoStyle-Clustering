class DNA:
    def __init__(self, seq):
        self.seq = seq

    def to_rna(self):
        return ''.join(('U' if c == 'T' else c) for c in self.seq)
