ADENINE = "A"
CYTOSINE = "C"
GUANINE = "G"
THYMIDINE = "T"
URACIL = "U"


class DNA(object):
    def __init__(self, sequence):
        self.sequence = sequence

    def to_rna(self):
        return self.sequence.replace(THYMIDINE, URACIL)
