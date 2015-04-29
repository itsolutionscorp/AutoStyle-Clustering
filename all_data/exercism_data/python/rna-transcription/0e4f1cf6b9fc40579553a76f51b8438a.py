from string import maketrans, translate


TO_RNA = maketrans('GCTA', 'GCUA')


class DNA:
    def __init__(self, sequence=""):
        self.sequence = sequence

    def to_rna(self):
        return translate(self.sequence, TO_RNA)
