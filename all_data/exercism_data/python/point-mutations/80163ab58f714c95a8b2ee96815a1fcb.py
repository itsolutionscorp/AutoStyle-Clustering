class DNA(object):

    def __init__(self, strand):
        self.strand = strand

    def hamming_distance(self, strand):
        return len([(x,y) for (x,y) in zip(strand, self.strand) if
            x != y])
