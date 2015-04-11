class DNA(object):
    '''Represents a strand of DNA'''

    def __init__(self, base_code):
        '''creates DNA for provided base code'''
        self.code = base_code

    def to_rna(self):
        '''transcribes the DNA base code into RNA form'''
        # base code transcription rules
        # C = C, G = G, A = A, T = U
        return self.code.replace('T','U')
