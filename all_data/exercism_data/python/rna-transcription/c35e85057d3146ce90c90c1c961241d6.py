# First, let's establish the convertion table between dna and rna
DNA_RNA = {
    'A': 'A',
    'C': 'C',
    'G': 'G',
    'T': 'U'
    }


class DNA:
    def __init__(self, strand):
        self.strand = strand

    def to_rna(self):
        rna_strand = map(DNA_RNA.__getitem__, self.strand)
        return ''.join(rna_strand)
