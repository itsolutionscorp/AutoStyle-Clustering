class DNA(object):
    def __init__(self, nucleotides_seq):
        self.seq = nucleotides_seq

    def to_rna(self):
        return self.seq.replace('T', 'U')
