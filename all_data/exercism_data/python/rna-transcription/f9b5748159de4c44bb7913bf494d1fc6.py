class DNA(object):

    def __init__(self, dna):
        self.dna = dna

    def to_rna(self):
        return self.dna.replace('A', 'U').replace('T', 'A').replace('C',
                'X').replace('G', 'C').replace('X', 'G')
