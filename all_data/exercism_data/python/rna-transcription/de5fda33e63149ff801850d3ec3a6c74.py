class DNA(object):


    def __init__(self, dna):
        self.dna = dna

        self.transform = {
                'A': 'U',
                'T': 'A',
                'C': 'G',
                'G': 'C',
                }

    def to_rna(self):
        return ''.join([self.transform[i] for i in self.dna])
