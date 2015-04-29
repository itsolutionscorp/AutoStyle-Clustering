class DNA(object):
    DNA_TO_RNA = {
        'A': 'A',
        'C': 'C',
        'G': 'G',
        'T': 'U',
    }

    def __init__(self, sequence):
        self.sequence = sequence

    def to_rna(self):
        rna = []
        for nucleotide in self.sequence:
            rna.append(self.DNA_TO_RNA[nucleotide])
        return ''.join(rna)
