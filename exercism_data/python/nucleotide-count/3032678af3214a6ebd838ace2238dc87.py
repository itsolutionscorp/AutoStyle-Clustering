import collections

class DNA:
    def __init__(self, dna):
        self.counts = { 'A': 0, 'C': 0, 'T': 0, 'G': 0 }
        self.counts.update(collections.Counter(dna))
        return

    def count(self, nucleotide):
        if nucleotide == 'U':
            return 0

        if nucleotide in self.counts:
            return self.nucleotide_counts()[nucleotide]

        raise ValueError("{} is not a nucleotide.".format(nucleotide))

    def nucleotide_counts(self):
        return self.counts

    pass
