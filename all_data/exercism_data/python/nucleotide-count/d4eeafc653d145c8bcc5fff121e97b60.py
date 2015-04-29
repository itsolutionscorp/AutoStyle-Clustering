class DNA(object):

    nucleotides = "ACGT"

    def __init__(self, strand):
        self.strand = strand

    def count(self, nucleotide):
        if nucleotide in DNA.nucleotides:
            count = self.strand.count(nucleotide)
        elif nucleotide == 'U':
            count = 0
        else:
            raise ValueError, nucleotide + " is not a nucleotide."
        return count

    def nucleotide_counts(self):
        counts = {}
        for nucleotide in DNA.nucleotides:
            counts[nucleotide] = self.strand.count(nucleotide)
        return counts
