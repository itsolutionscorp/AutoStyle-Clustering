class DNA(object):
    RNA_MAPPING = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }

    def __init__(self, sequence):
        self.sequence = sequence

    def to_rna(self):
        return ''.join(map(DNA.RNA_MAPPING.get, self.sequence.upper()))
