class DNA(object):
    def __init__(self, dna_str):
        self.nucleotides = { 'A': 0, 'C': 0, 'G': 0, 'T': 0 }
        for nuc in dna_str:
            if nuc in self.nucleotides:
                self.nucleotides[nuc] += 1
            else:
                self.nucleotides[nuc] = 1

    def count(self, nucleotide):
        nucs_plus_u = dict(self.nucleotides)
        nucs_plus_u['U'] = 0
        if nucleotide in nucs_plus_u:
            return nucs_plus_u[nucleotide]
        raise ValueError('%s is not a nucleotide.' %(nucleotide))

    def nucleotide_counts(self):
        return self.nucleotides
