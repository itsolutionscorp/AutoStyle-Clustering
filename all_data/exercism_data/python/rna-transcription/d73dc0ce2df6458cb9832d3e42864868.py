class DNA(object):
    def __init__(self, dna):
        self.dna = dna
        self.DNA_MAP = {'G' : 'G',
                        'C' : 'C',
                        'T' : 'U',
                        'A' : 'A'}
    def to_rna(self):
        """Transcribe DNA string to RNA string."""
        rna = ""
        if self.dna:
            rna = "".join(map(lambda x: self.DNA_MAP[x], self.dna))
        return rna
