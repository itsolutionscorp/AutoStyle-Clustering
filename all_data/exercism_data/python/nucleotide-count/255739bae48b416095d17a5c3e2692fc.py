class DNA:

    dna_nucleotides = ["A", "C", "G", "T"]
    rna_nucleotides = ["A", "C", "G", "U"]
    valid_nucleotides = list(set(dna_nucleotides + rna_nucleotides))

    def __init__(self, dna):
        self.dna = dna

    def count(self, nucleotide):
        if not nucleotide in self.valid_nucleotides:
            raise ValueError(nucleotide + ' is not a nucleotide.')
        return self.dna.count(nucleotide)

    def nucleotide_counts(self):
        return { n: self.count(n) for n in self.dna_nucleotides }
