class DNA(object):

    def __init__(self, dna):
        self.dna = dna.upper()

    def to_rna(self):
        return self.dna.replace("T", "U")
