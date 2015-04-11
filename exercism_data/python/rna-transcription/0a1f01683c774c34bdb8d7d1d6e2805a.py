class DNA:
    TO_RNA_MAP = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U',
    }

    def __init__(self, nucleotides):
        self.nucleotides = nucleotides

    def to_rna(self):
        rna = ''
        for n in self.nucleotides:
            rna += DNA.TO_RNA_MAP[n]
        return rna
