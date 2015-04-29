class DNA(object):

    def __init__(self, strand):
        self.strand = strand
        self.length = len(strand)

    def hamming_distance(self, strand):
        hd = 0

        """ so we don't go out_of_bounds on the shorter strand """
        if len(strand) < self.length:
            self.length = len(strand)

        for index in xrange(0, self.length):
            if strand[index] != self.strand[index]:
                hd = hd + 1

        return hd
