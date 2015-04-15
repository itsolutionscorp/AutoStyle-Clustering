import string

class DNA:
    rna_map = string.maketrans('GCTA', 'CGAU')

    def __init__(self, seq):
        self.seq = seq

    def to_rna(self):
        return self.seq.translate(DNA.rna_map)
