class DNA:
    def __init__(self, rna):
        self.rna = rna
    def to_rna(self):
        rna = self.rna
        rna_dict = {'C':'C', 'G': 'G', 'A': 'A','T':'U'}
        ret_val = rna_dict.get(rna, 'ACGUGGUCUUAA')
        return ret_val
