class DNA(object):

    def __init__(self, nucleotides):
        self.nucleotides = nucleotides

    def count(self, nucleotide):
        if not nucleotide in 'ACGTU':
            raise ValueError('. is not a nucleotide.')
        return self.nucleotides.count(nucleotide)

    def nucleotide_counts(self):
        return dict((nucleotide, self.count(nucleotide)) for nucleotide in 'ACGT')
