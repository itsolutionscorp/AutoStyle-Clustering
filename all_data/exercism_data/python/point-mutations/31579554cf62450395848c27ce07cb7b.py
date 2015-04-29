class DNA:
    def __init__(self, dna):
        self.dna = dna

    def hamming_distance(self, s):
        return sum(a != b for a, b in zip(self.dna, s))
