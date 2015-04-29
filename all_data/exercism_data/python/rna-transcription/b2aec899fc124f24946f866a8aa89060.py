class DNA:
    """A piece of DNA"""
    def __init__(self, dna):
        self.dna = dna

    def to_rna(self):
        """Return the RNA representation of self"""
        return self.dna.replace("T", "U")
