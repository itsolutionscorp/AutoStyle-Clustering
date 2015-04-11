class DNA:
    
    dna_to_rna = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }

    def __init__(self, strand):
        self.strand = strand

    def to_rna(self):
        rna = []
        for c in self.strand:
            rna.append(self.dna_to_rna[c])
        return ''.join(rna)
