class DNA(object):
    def __init__(self, nucleotides):
        """Set DNA nucleotides to a sequence of A, C, G, T,
        each letter respresenting a different nucleotide"""
        self._nucleotides = nucleotides.upper()

    def to_rna(self):
        """Return the DNA nucleotides sequence converted to RNA"""
        return self._nucleotides.replace('T', 'U')
