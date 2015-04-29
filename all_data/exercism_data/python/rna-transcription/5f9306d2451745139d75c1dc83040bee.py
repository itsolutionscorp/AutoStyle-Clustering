class DNA:
    DNA_TO_RNA = str.maketrans("CTAG", "GAUC")

    def __init__(self, dna_string):
        self.dna_string = dna_string

    def to_rna(self):
        return self.dna_string.translate(self.DNA_TO_RNA)
