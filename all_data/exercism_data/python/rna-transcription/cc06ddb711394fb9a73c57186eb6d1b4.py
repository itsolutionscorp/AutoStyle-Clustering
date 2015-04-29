class DNA:
    rna_map = {
            'G': 'C',
            'C': 'G',
            'T': 'A',
            'A': 'U'
    }

    def __init__(self, seq):
        self.seq = seq

    def to_rna(self):
        return ''.join([ DNA.rna_map[n] for n in self.seq ])
