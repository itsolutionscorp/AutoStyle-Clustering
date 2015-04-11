'''
DNA-RNA Translator
'''

class DNA(object):
    '''
    DNA Module
    '''
    def __init__(self, nucleotides=None):
        self.nucleotides = nucleotides

    def to_rna(self):
        '''
        Translate DNA to RNA
        '''
        translated_nucleotides = ''
        for nucleotide in self.nucleotides:
            if nucleotide is 'T':
                nucleotide = 'U'
            translated_nucleotides += nucleotide
        return  translated_nucleotides
