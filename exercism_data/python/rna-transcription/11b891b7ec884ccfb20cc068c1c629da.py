class DNA(object):
    '''Represents a strand of DNA'''

    def __init__(self, strand_nucleotide_sequence):
        '''creates DNA for provided base code
        note the strand is a string sequence of
        DNA nucleotide letter codes
        representing the coding sequence
        (not the transcription sequence)
        '''
        self.strand = nucleotide_sequence

    def to_rna(self):
        '''transcribes the DNA coding sequence into RNA form
        base code transcription rules (by code letter)
        C = C,
        G = G,
        A = A,
        T = U
        '''
        return self.strand.replace('T','U')
