class DNA():
    def __init__(self, dna_string):
        self.dna_string = dna_string

    def to_rna(self):
        thymine = "T"
        uracil = "U"
        return self.dna_string.replace(thymine, uracil)
