class DNA:

    dna_rna_dict = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

    def __init__(self, code):
        self.code = code

    def to_rna(self):
        try:
            rna = ''
            for item in self.code:
                rna += self.dna_rna_dict[item]
        except:
            return "Not a valid nucleotide"
        return rna
