import collections

class DNA(object):
    """Class representing DNA strand."""
    def __init__(self, strand):
        self.strand = strand

    def count(self, nucleotide):
        """Return count of given nucleotide in current DNA strand."""
        if nucleotide not in "ACTGU":
            raise ValueError(nucleotide+" is not a nucleotide.")
        else:
            return collections.Counter(self.strand)[nucleotide]

    def nucleotide_counts(self):
        """Return dictionary with counts of all nucleotides."""
        c = collections.Counter(self.strand)
        if not 'A' in c.keys():
            c['A'] = 0
        if not 'C' in c.keys():
            c['C'] = 0
        if not 'T' in c.keys():
            c['T'] = 0
        if not 'G' in c.keys():
            c['G'] = 0
        return c
