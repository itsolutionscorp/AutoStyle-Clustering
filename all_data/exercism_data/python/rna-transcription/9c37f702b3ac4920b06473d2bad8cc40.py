class DNA(object):
    """DNA Class"""

    # Mapping
    MAP = {'G':'C', 'C':'G', 'T':'A', 'A': 'U'}
    def __init__(self, nucleotide):
        super(DNA, self).__init__()
        self.nucleotide = nucleotide
    
    def to_rna(self):
        '''Iterate through each nucleotide character and replace it with the 
        corresponding characrter in the DNA.MAP'''
        return ''.join([DNA.MAP[k] for k in self.nucleotide])


        
