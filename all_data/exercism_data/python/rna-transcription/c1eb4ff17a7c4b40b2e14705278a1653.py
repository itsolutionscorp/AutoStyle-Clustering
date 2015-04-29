class DNA:
    DNA_TO_RNA_MAPPING = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }

    def __init__(self, RNA):
        self.RNA = RNA

    def to_rna(self):
        return ''.join([self.DNA_TO_RNA_MAPPING[element] for element in self.RNA])
