class DNA(object):
    def __init__(self, sequence):
        self.sequence = sequence
    
    def hamming_distance(self, new_sequence):
        return reduce(
            lambda acc, p: acc + int(p[0] != p[1]),
            zip(self.sequence, new_sequence), 0)
