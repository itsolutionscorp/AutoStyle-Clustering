class DNA(object):

    def __init__(self, dna1):
        self.dna1 = dna1

    def hamming_distance(self, dna2):

        count = 0
        for i in range(0, min(len(self.dna1), len(dna2))):
            if self.dna1[i] != dna2[i]:
                count += 1
        return count
