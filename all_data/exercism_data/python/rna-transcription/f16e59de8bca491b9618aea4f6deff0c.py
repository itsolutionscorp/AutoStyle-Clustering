DNA_TO_RNA = {
    'A': 'A',
    'C': 'C',
    'G': 'G',
    'T': 'U',
}

class DNA(object):
    def __init__(self, nucleotides):
        self.nucleotides = nucleotides

    def to_rna(self):
        return ''.join((DNA_TO_RNA[n] for n in self.nucleotides))
