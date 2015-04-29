class DNA(object):
    tr = str.maketrans("ACGT", "UGCA")

    def __init__(self, s):
        self.s = s

    def to_rna(self):
        return self.s.translate(DNA.tr)
