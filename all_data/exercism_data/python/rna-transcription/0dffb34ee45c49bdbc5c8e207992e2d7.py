class DNA(object):
    def __init__(self, dna_string):
        self._dna = dna_string

    def to_rna(self):
        return self._dna.replace('T', 'U')
