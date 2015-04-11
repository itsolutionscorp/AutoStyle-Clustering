class DNA:
    def __init__(self, dna):
        self.sequence = dna
        self.transcribe_to_rna = {
            "G": "C",
            "C": "G",
            "T": "A",
            "A": "U"
        }

    def to_rna(self):
        rna = ""
        for dna in self.sequence:
            rna += self.transcribe_to_rna[dna]
        return rna
