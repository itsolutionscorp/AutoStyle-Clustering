class DNA:

    def __init__(self, string):
        self.string = string

    def hamming_distance(self, other):
        score = 0
        end_range = self.range(other)
        for index in range(0, end_range):
            score += self.compare(other, index)
        return score

    def compare(self, other, index):
        if self.string[index] == other[index]:
            return 0
        else:
            return 1

    def range(self, other):
        return min([len(self.string), len(other)])
