import string


class DNA(object):
    TRANSLATION_TABLE = string.maketrans('GCTA', 'CGAU')

    def __init__(self, dna):
        self.dna = dna

    def to_rna(self):
        return self.dna.translate(DNA.TRANSLATION_TABLE)
