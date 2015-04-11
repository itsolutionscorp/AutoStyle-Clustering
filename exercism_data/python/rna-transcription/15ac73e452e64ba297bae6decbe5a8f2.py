dna_to_rna = {"A": "A", "C": "C", "G": "G", "U": "U", "T": "U"}
class DNA(object):
    def __init__(self, v):
        self.v = v

    def to_rna(self):
        return "".join([dna_to_rna[c] for c in self.v])
