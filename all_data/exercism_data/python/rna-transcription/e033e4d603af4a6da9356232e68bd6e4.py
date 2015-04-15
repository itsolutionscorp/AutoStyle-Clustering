class DNA():
    
    def __init__(self, dna):
        self.dna = dna
    
    def to_rna(self):
        rna = self.dna.replace("T", "U")
        return rna
