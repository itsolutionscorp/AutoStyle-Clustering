from operator import ne


class DNA(object):

    def __init__(self, strand):
        self.strand = strand

    def hamming_distance(self, strand):
        return len([pair for pair in zip(self.strand, strand) if ne(*pair)])
