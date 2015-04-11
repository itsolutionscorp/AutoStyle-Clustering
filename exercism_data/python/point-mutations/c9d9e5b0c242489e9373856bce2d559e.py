class DNA(object):
    def __init__(self, strand):
        self.strand = strand

    def hamming_distance(self, otherstrand):
        return len(
                 filter(
                   lambda (x,y): x != y,
                   zip(self.strand, otherstrand) ))
