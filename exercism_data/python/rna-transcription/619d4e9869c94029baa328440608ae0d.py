class DNA(object):

    def __init__(self, nucleotide_sequence):
        self.sequence = nucleotide_sequence

    def to_rna(self):
        return ''.join([self.complement(nucleotide) for nucleotide in self.sequence])

    @staticmethod
    def complement(nucleotide):
        complements = {
            'G': 'C',
            'C': 'G',
            'T': 'A',
            'A': 'U',
        }
        return complements[nucleotide]
