class DNA(object):
    def __init__(self, dna_str):
        self._dna_str = dna_str

    def to_rna(self):
        rna_sequence = map(lambda c: 'U' if c == 'T' else c, self._dna_str)
        return ''.join(rna_sequence)
