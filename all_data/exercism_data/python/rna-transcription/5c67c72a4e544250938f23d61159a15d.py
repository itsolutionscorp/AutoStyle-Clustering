class DNA(object):
    
    def __init__(self, dna, *args, **kwargs):
        self.dna = dna

    def to_rna(self):
        return self.dna.replace('A','U').replace('T','A').replace('C','Z').replace('G','C').replace('Z','G')
