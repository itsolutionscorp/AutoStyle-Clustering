class WrongDNA(Exception):
    pass

class DNA(object):
    def __init__(self, dna_string):
        erroneous_characters = dna_string.translate(None, "CGAT")
        if erroneous_characters:
            raise WrongDNA
        self.dna_string = dna_string

    def to_rna(self):
        rna_string = str()
        for c in self.dna_string:
            if c == 'T':
                rna_string += 'U'
            else:
                rna_string += c
        return rna_string
