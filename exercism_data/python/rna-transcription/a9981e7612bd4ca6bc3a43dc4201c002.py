def to_rna(strand):
    rna = RNA()
    return rna.fromDna(strand)

class RNA(object):
    '''
    Holds a RNA strand representation, with the ability to translate from DNA strand
    '''
    #Dictionary holding DNA to RNA translation. Keys are DNA, valures are RNA strands
    rna2DnaTransDict = {'G' : 'C', 'C' : 'G', 'T' : 'A', 'A' : 'U'}
    
    def __init__(self):
        self.rna = ''
    
    def fromDna(self, dna):
        '''
        Given a DNA strand string, returns the RNA strand complement
        '''
        for strand in dna:
            self.rna += self.translateDnaStrand(strand)
        return self.rna
        
    def translateDnaStrand(self, strand):
        '''
        Given a single DNA strand character, returns the RNA complement
        '''
        return self.rna2DnaTransDict[strand]
