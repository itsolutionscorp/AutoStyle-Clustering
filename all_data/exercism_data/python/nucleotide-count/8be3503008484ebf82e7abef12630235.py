class DNA:

    """DNA strand"""

    VALID_NUCLEOTIDES = ('A', 'T', 'C', 'G', 'U')
    DNA_NUCLEOTIDES = ('A', 'T', 'C', 'G')

    def __init__(self, sequence):
        """Create a new DNA strand from a string."""
        self.sequence = sequence.upper()

    def count(self, nucleotide):
        """Return how many times given nucleotide appears."""
        if nucleotide in self.VALID_NUCLEOTIDES:
            return self.sequence.count(nucleotide)
        else:
            raise ValueError("{0} is not a nucleotide.".format(nucleotide))

    def nucleotide_counts(self):
        """Return counts of all nucleotides as a dict."""
        return {n:self.count(n) for n in self.DNA_NUCLEOTIDES}
