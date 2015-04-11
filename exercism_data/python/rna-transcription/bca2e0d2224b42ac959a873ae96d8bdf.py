class DNA(object):
    def __init__(self, sequence):
        self.sequence = sequence.upper().strip()

    def to_rna(self):
        '''
        Converts a DNA sequence to an RNA sequence.
        '''
        return self.sequence.replace('T', 'U')
