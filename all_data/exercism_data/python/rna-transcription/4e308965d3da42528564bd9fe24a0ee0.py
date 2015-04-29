dna_to_rna = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
    }
    
class DNA:
    def __init__(self, strand):
        self.strand = list(strand)

    def to_rna(self):
        return "".join([dna_to_rna[s] for s in self.strand])

    
