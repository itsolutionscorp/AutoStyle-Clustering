class DNA:
    def __init__(self, dna_string):
        self.dna_string = dna_string
        self.translation_table = str.maketrans("CTAG", "GAUC")

    def to_rna(self):
        return self.dna_string.translate(self.translation_table)
