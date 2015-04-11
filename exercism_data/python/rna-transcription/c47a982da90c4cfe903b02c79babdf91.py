THYMIDINE = "T"
URACIL = "U"

class DNA(object):
    def __init__(self, dna_string):
        self._dna_string = dna_string

    def to_rna(self):
        return self._dna_string.replace(THYMIDINE, URACIL)
