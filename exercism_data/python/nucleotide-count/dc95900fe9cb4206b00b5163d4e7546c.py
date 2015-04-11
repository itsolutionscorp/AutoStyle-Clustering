NUCLEOTIDES = ('A','T','C','G')
VALID_NUCLEOTIDES = ('A', 'T', 'C', 'G', 'U')

class DNA(object):
    def __init__(self, dna):
        self.dna = dna

    def count(self, nucleotide):
        self.test(nucleotide)
        return self.dna.count(nucleotide)

    def nucleotide_counts(self):
        answer = {}
        for nucleotide in NUCLEOTIDES:
            answer[nucleotide] = self.dna.count(nucleotide)
        return answer

    def test(self, nucleotide):
        if nucleotide not in VALID_NUCLEOTIDES:
            raise ValueError("{0} is not a nucleotide.".format(nucleotide))
