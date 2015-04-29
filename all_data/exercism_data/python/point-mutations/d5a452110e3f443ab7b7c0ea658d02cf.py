class DNA(object):
    def __init__(self, seq):
        self.sequence = seq

    def hamming_distance(self, seq):
        return len([c1 for (c1, c2) in zip(seq, self.sequence) if c1 != c2])
