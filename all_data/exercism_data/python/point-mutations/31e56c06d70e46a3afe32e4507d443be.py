class DNA(object):
    def __init__(self, strand):
        self._strand = strand

    def hamming_distance(self, other):
        count = 0
        for x, y in zip(self._strand, other):
            if x != y:
                count += 1
        return count
