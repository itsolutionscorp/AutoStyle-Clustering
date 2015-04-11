class DNA(object):
    t = str.maketrans("ATCG", "UAGC")
    def __init__(self, s):
        self.s = s
    def to_rna(self):
        return self.s.translate(DNA.t)
