class DNA:
    def __init__(self, dna):
        self.dna = dna
        self.nucleotides = { 'A': 0, 'T': 0, 'C': 0, 'G': 0 }
        for n in self.dna:
            self.nucleotides[n] += 1

    def count(self, n):
        if n == 'U':
            return 0
        if n not in self.nucleotides:
            raise ValueError("%s is not a nucleotide." % n)
        return self.nucleotides[n]

    def nucleotide_counts(self):
        return self.nucleotides
