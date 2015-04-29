def to_rna(self):
    return self.translate(self.maketrans('GCTA','CGAU'))
