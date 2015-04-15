class DNA:
    dna_to_rna_mapping = {
        "G": "C",
        "C": "G",
        "T": "A",
        "A": "U"
    }

    def __init__(self,dna):
        self.dna = dna

    def to_rna(self):
        return ''.join([DNA.dna_to_rna_mapping[x] for x in self.dna])
