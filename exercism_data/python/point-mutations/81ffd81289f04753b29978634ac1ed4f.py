class DNA(object):
    def __init__(self, sequence):
        self.sequence = sequence

    def hamming_distance(self, other_sequence):
        n = 0
        for char1, char2 in zip(self.sequence, other_sequence):
            if char1 != char2:
                n += 1
        return n
