from string import maketrans

class DNA(object):
    _transcriptor = maketrans('GCTA', 'CGAU')

    def __init__(self, dna):
        self.dna = dna

    def to_rna(self):
        return self.dna.translate(DNA._transcriptor)
