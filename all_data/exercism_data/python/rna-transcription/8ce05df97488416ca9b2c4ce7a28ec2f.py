class DNA:
    def __init__(self, dna_string):
        self.dna_string = dna_string

    def to_rna(self):
        from string import maketrans
        intab = "GCTA"
        outtab = "CGAU"
        trantab = maketrans(intab, outtab)
        return self.dna_string.translate(trantab)
