class DNA:
    def __init__(self, rna):
        self.rna = rna

    def to_rna(self):
        _ = list(self.rna)
        for i, c in enumerate(_):
            if c == 'G':
                _[i] = 'C'
            if c == 'C':
                _[i] = 'G'
            if c == 'T':
                _[i] = 'A'
            if c == 'A':
                _[i] = 'U'
        return "".join(_)
