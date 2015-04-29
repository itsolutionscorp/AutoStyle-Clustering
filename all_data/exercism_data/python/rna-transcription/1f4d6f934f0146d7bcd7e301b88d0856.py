'''
DNA-RNA Translator
'''

class DNA(object):
    '''
    DNA Module
    '''
    ACID_TABLE = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    def __init__(self, nucleotides):
        self.nucleotides = nucleotides

    def to_rna(self):
        '''
        Translates DNA to RNA using a quick lookup table.
        '''
        self.rna = ""
        for acid in self.nucleotides:
            self.rna += self.ACID_TABLE[acid]
        return self.rna
