DNA_NUCLEOTIDES = ['A', 'C', 'G', 'T']
ALL_NUCLEOTIDES = ['A', 'C', 'G', 'T', 'U']

def is_nucleotide(possible_nucleotide):
    return possible_nucleotide in ALL_NUCLEOTIDES

class DNA(object):
    '''Represents a strand of DNA'''

    def __init__(self, strand_nucleotide_sequence):
        '''creates DNA for provided base code
        note the strand is a string sequence of
        DNA nucleotide letter codes
        representing the coding sequence
        (not the transcription sequence)
        '''
        self.strand = strand_nucleotide_sequence

    def count(self, nucleotide):
        if not is_nucleotide(nucleotide):
            raise ValueError(nucleotide + " is not a nucleotide.")

        return self.strand.count(nucleotide)

    def nucleotide_counts(self):
        return {nucleotide: self.count(nucleotide)
                for nucleotide in DNA_NUCLEOTIDES}
