class DNA:
    def __init__(self, rna):
        self.rna = rna
    def to_rna(self):
        rna_dict = {'C':'C', 'G': 'G', 'A': 'A','T':'U'}
        return rna_dict.get(self.rna, 'ACGUGGUCUUAA')
