class DNA():

    def __init__(self, dna_strand):
        self.dna_strand = dna_strand

    def to_rna(self):
        """ Transcribes DNA strands to RNA strands """
        return self.dna_strand.replace('T', 'U')
