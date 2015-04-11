class DNA:
    def __init__(self, dna):
        self.dna = dna
        
    def to_rna(self):
        return ''.join(map(lambda x: 'U' if x == 'T' else x, self.dna))
