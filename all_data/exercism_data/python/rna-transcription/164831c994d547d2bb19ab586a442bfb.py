compliments = {"G":"C",
               "C":"G",
               "T":"A",
               "A":"U"}

class DNA:
    def __init__(self, sequence):
        self.strand = sequence

    def to_rna(self):
        rna = [compliments[x] for x in self.strand]

        return "".join(rna)
