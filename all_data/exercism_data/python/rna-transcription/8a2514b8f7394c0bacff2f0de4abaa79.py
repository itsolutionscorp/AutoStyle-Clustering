class DNA:
    def __init__(self, nucleotides):
        self.nucleotides = nucleotides

    def to_rna(self):
        return self.nucleotides.translate(str.maketrans("T", "U"))
