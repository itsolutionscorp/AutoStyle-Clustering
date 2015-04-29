class DNA(object):
    """

    """
    def __init__(self, dna):
        self.nucleotides = {'A': 0, 'T': 0, 'C': 0, 'G': 0}
        self.dna         = dna
        for c in self.dna:
            try:
                self.nucleotides[c.upper()] += 1
            except KeyError:
                raise ValueError, "Invalid Nucleotide", c

    def count(self, n):
        if n not in self.nucleotides.keys() + ['U']:
            raise ValueError, n + " is not a nucleotide."
        return self.nucleotides.get(n, 0)

    def nucleotide_counts(self):
        return self.nucleotides
