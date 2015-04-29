class DNA:
    """ DNA determines hamming distance between two different DNA strands """
    def __init__(self, dnaString):
        self.dnaString = dnaString

    def hamming_distance(self, otherString):
        count = 0
        for i, s in enumerate(otherString):
            if i < len(self.dnaString) and s != self.dnaString[i]:
                count += 1
        return count
