from collections import Counter

class DNA(object):
    def __init__(self, strand):
        self.strand = strand

    def count(self, nucleotide):
        if nucleotide not in "ATCGU":
            raise ValueError("{0} is not a nucleotide.".format(nucleotide))

        count = 0
        for n in self.strand:
            if n == nucleotide:
                count += 1
        return count

    def nucleotide_counts(self):
        counter = Counter(A=0, T=0, C=0, G=0)
        counter.update(self.strand)
        return counter
