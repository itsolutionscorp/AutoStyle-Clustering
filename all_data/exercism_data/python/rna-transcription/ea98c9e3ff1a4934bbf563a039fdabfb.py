class DNA(object):
    def __init__(self, dna_str):
        self._dna_str = dna_str

    def to_rna(self):
        return self._dna_str.replace('T', 'U')
