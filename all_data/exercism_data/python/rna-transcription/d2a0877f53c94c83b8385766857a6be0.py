class DNA(object):

    replaces = {'G':'C','C':'G', 'T':'A', 'A':'U'}

    def __init__(self, dna):
        self.dna = dna
    
    def to_rna(self):
        return ''.join([self.replaces[i] for i in self.dna])
