class DNA(object):
    '''
    Transcribe the DNA to the complement RNA
    '''
    
    comp = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    
    def __init__(self, dna):
        self.dna = dna
    
    def to_rna(self):
        return ''.join(self.comp[n] for n in self.dna)
