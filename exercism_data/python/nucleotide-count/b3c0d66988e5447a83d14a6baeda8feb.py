nucleotides = ('A','T','C','G')

class DNA(object):
    def __init__(self, dna):
        self.dna = dna

    def count(self, nucleotide):
        return self.dna.count(nucleotide)

    def nucleotide_counts(self):
        answer = {}
        for nucleotide in nucleotides:
            answer[nucleotide] = self.dna.count(nucleotide)
        return answer
