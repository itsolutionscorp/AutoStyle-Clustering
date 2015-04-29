class DNA(object):
    def __init__(self, nucleotides):
        self.nucleotides = nucleotides

    def to_rna(self):
        return self.nucleotides.replace('T', 'U')
