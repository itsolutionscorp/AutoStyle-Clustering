from collections import Counter

class DNA(object):
    def __init__(self, dna):
        self.dna = dna
        self.nucleotides = ["A", "T", "C", "G"]

    def nucleotide_counts(self):
        nucleo_counts = Counter(self.dna)
        for n in self.nucleotides:
            if n not in nucleo_counts:
                nucleo_counts[n] = 0
        return nucleo_counts

    def count(self, nucleotide):
        if nucleotide in self.nucleotides:
            return self.nucleotide_counts()[nucleotide]
        elif nucleotide == 'U':
            return 0
        else:
            raise ValueError('%s is not a nucleotide.' % nucleotide)
