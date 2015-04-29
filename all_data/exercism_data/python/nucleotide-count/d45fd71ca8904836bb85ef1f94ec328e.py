import re

class DNA:

    def __init__(self, nucleotides):
        self.nucleotides = nucleotides

    def count(self, nucleotide):
        if not re.match('[ATCGU]', nucleotide):
            raise ValueError(nucleotide + ' is not a nucleotide.')
        else:
            return self.nucleotides.count(nucleotide)

    def nucleotide_counts(self):
        count = {}
        for letter in ['A', 'T', 'C', 'G']:
            count[letter] = self.nucleotides.count(letter)
        return count
