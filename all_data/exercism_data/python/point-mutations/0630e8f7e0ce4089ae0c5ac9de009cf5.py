class DNA:
    def __init__(self, nucleotides):
        self.nucleotides = nucleotides

    def hamming_distance(self, nucleotides):
        return sum(map(lambda arg: 1 if arg[0] != arg[1] else 0, zip(self.nucleotides, nucleotides)))
