class DNA(str):
    def hamming_distance(self, other):
        return sum(c1 != c2 for c1, c2 in zip(self, other))
