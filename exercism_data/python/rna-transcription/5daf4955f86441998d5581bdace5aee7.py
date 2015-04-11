class DNA(object):
    def __init__(self, nucleotides):
        self._nucleotides = nucleotides.upper()

    def to_rna(self):
        return self._nucleotides.replace('T', 'U')
