from collections import Counter
class DNA:
    valid_nucleotides = frozenset(['A','G','T','C','U'])

    def __init__(self, nucleotides):
        self.counts = dict({'A' : 0, 'G' : 0, 'T' : 0, 'C' : 0}.items() +
                Counter(nucleotides).items())

    def count(self, nucleotide):
        if nucleotide not in self.valid_nucleotides:
            raise ValueError(nucleotide + ' is not a nucleotide.')
        return self.counts.get(nucleotide, 0)

    def nucleotide_counts(self):
        return self.counts
