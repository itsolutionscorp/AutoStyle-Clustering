class DNA(object):
    NUCLEOTIDES = 'ACGT'

    def __init__(self, strand):
        self._strand = strand

    def count(self, nucleotide):
        if nucleotide not in self.NUCLEOTIDES and nucleotide != 'U':
            raise ValueError("%s is not a nucleotide." % nucleotide)
        return self._strand.count(nucleotide)

    def nucleotide_counts(self):
        counts = dict((n, 0) for n in self.NUCLEOTIDES)
        for c in self._strand:
            counts[c] += 1
        return counts
