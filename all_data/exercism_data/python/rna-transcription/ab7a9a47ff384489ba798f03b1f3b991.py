class DNA:
    def __init__(self, dna):
        self.dna = dna

    def to_rna(self):
        rna = ''
        for compl in self.dna:
            if compl == 'G':
                rna += 'C'
            elif compl == 'C':
                rna += 'G'
            elif compl == 'T':
                rna += 'A'
            else:
                rna += 'U'
        return rna
