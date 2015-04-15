class DNA:
    VALID_DNA_NUCLEOTIDES = ('A', 'C', 'G', 'T')
    VALID_RNA_NUCLEOTIDES = ('A', 'C', 'G', 'U')
    VALID_NUCLEOTIDES = set(VALID_DNA_NUCLEOTIDES + VALID_RNA_NUCLEOTIDES)

    def __init__(self, nucleotides):
        self.nucleotides = nucleotides

    def count(self, nucleotide):
        if nucleotide not in self.VALID_NUCLEOTIDES:
            raise ValueError('%s is not a nucleotide.' % nucleotide)

        return self.nucleotides.count(nucleotide)

    def nucleotide_counts(self):
        return dict((nucleotide, self.count(nucleotide)) for nucleotide in self.VALID_DNA_NUCLEOTIDES)
