import string

class DNA(object):
    "Second python exercise in exercism"

    rna_translation = string.maketrans("GCTA", "CGAU")

    def __init__(self, text):
        "Create a dna sequence from a string"
        # No error checking. YAGNI
        self.dna = text

    def to_rna(self):
        "Return the rna sequence corresponding to the dna"
        return self.dna.translate(DNA.rna_translation)
