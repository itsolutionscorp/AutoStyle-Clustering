class DNA(object):
    def __init__(self, strand):
        self.strand = strand
        self.nucleotides = 'ATCGU'

    def count(self, nucleotide):
        if nucleotide not in self.nucleotides:
            raise ValueError("{} is not a nucleotide.".format(nucleotide))
        elif nucleotide == 'U':
            return 0
        else:
            return self.nucleotide_counts()[nucleotide]

    def nucleotide_counts(self):
        counter = {'A': 0, 'T': 0, 'C': 0, 'G': 0}
        for x in self.strand:
            counter[x] += 1
        return counter
