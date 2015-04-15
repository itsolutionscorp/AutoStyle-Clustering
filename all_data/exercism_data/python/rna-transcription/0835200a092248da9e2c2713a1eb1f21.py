class DNA:
    def __init__(self, strand):
        self.str = strand

    def to_rna(self):
        list = [self.converter(c) for c in self.str]
        return "".join(list)

    def converter(self, s):
        if s == "G":
            return "C"
        elif s == "C":
            return "G"
        elif s == "T":
            return "A"
        elif s == "A":
            return "U"
