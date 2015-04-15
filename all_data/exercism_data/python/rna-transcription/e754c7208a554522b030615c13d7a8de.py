import string

class DNA:
    def __init__(self, s):
        tr = string.maketrans("GCTA", "CGAU")
        self.s = s.translate(tr)

def to_rna(self):
        return self.s
