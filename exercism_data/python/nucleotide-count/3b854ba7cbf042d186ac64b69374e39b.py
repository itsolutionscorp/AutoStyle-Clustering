class DNA(object):
    def __init__(self, strand):
        self.strand = strand
        self.count_nucleotides()

    def count(self, nucleotide):
        if nucleotide == 'U':
            return 0
        elif nucleotide in self.counts:
            return self.counts[nucleotide]
        else:
            raise ValueError("{n} is not a nucleotide.".format(n=nucleotide))

    def nucleotide_counts(self):
        return self.counts

    def count_nucleotides(self):
        self.counts = {'A': 0, 'T': 0, 'C': 0, 'G': 0}
        for nucleotide in self.strand:
            self.counts[nucleotide] += 1
