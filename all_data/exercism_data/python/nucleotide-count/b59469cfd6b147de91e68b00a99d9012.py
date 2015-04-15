class DNA(object):
    def __init__(self, dna_str):
        self.dna = dna_str
        self.nucleotides = { 'A': 0, 'C': 0, 'G': 0, 'T': 0 }
        for e in self.dna:
            if e in self.nucleotides:
                self.nucleotides[e] = self.nucleotides[e] + 1
            else:
                self.nucleotides[e] = 1

    def count(self, dna_str):
        nucs_plus_u = dict(self.nucleotides)
        nucs_plus_u['U'] = 0
        if dna_str in nucs_plus_u:
            return nucs_plus_u[dna_str]
        raise ValueError('%s is not a nucleotide.' %(dna_str))

    def nucleotide_counts(self):
        return self.nucleotides
