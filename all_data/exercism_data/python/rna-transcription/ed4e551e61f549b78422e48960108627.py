class DNA:
    dna_to_rna_table = {'T':'U'}

    def __init__(self, dna):
        self.dna = dna

    def dna_to_rna(self, base):
        return self.dna_to_rna_table.get(base, base)

    def to_rna(self):
        return "".join([self.dna_to_rna(base) for base in self.dna])
