class DNA:
    """ Represents a DNA sequence """
    def __init__(self, seq):
        self.dna_seq = seq
    def to_rna(self):
        """ Convert DNA to RNA and return the result"""
        return self.dna_seq.replace('T', 'U')
