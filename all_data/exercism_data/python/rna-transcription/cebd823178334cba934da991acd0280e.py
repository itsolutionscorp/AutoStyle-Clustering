
class DNA(object):
    def __init__(self, dna):
        self.dna = dna
        self.mappings = {'G' : 'C', 'C' : 'G', 'T' : 'A', 'A' : 'U'}

    def to_rna(self):
        return ''.join(self.mappings[x] for x in self.dna)
