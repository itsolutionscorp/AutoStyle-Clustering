class DNA:

    DNAPAIR = { 'G' : 'C', 'C' : 'G', 'T' : 'A', 'A' : 'U'}

    def __init__(self, nucleotides):
        self.nucleotides = list(nucleotides)

    def to_rna(self):
        rna = ''
        for n in self.nucleotides:
          rna += self.DNAPAIR[n]
        return rna
