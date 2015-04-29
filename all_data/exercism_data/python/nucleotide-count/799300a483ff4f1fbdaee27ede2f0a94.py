from collections import Counter

class DNA(object):
    nucleotides = 'ACGT'

    def __init__(self, dna):
        self.counted = Counter(dna)

    def count(self, n):
        if n == "U":
            return 0
        if n not in DNA.nucleotides:
            raise ValueError("{} is not a nucleotide.".format(n))
        return self.counted[n]

    def nucleotide_counts(self):
        return {n: self.counted[n] for n in DNA.nucleotides}
