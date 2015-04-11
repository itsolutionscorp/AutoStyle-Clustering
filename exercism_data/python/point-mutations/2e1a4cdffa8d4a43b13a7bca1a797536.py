from __future__ import unicode_literals

class DNA(object):
    def __init__(self, sequence):
        self.sequence = sequence

    def hamming_distance(self, other):
        return sum(int(a != b) for a, b in zip(self.sequence, other))
