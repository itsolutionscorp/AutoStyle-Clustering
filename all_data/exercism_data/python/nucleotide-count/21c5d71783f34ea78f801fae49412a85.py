import collections

class DNA(object):
    def __init__(self, strand):
        self.strand = strand

    def count(self, nucleotide):
        if not self.valid_nucleotide(nucleotide):
            raise ValueError(nucleotide + " is not a nucleotide.")
        return self.strand.count(nucleotide)

    def nucleotide_counts(self):
        counts = collections.Counter(A = 0, T = 0, C = 0, G = 0)
        counts.update(self.strand)
        return counts;

    def valid_nucleotide(self, nucleotide):
        return nucleotide in "GATCU"
