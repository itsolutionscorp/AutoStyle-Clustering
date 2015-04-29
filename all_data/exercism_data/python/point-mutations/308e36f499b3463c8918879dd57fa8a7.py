
class DNA(object):
    def __init__(self, dna):
        self.dna = dna

    def hamming_distance(self, totest):
        return sum([a != b for a, b in zip(self.dna, totest)])
