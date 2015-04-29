class DNA:
    def __init__(self, seq):
        self.seq = seq

    def to_rna(self):
        return self.seq.replace('T', 'U')
