from itertools import izip


class DNA(object):
    def __init__(self, strand):
        self.strand = strand

    def hamming_distance(self, strand):
        return sum(1 for a, b in izip(self.strand, strand) if a != b)
