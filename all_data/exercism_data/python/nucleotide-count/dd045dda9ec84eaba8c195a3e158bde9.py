class DNA:
    DNA_NUCLEOTIDES = ['A', 'T', 'C', 'G']
    VALID_NUCLEOTIDES = DNA_NUCLEOTIDES + ['U']

    def __init__(self, strand):
        self.strand = list(strand)

    def count(self, nucleotide):
        if nucleotide not in self.VALID_NUCLEOTIDES:
            raise ValueError(nucleotide + " is not a nucleotide.")
        return self.strand.count(nucleotide)

    def nucleotide_counts(self):
        counts = {}
        for n in self.DNA_NUCLEOTIDES:
            counts[n] = self.count(n)
        return counts
