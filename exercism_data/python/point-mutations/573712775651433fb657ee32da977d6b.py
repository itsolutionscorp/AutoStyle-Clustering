class DNA(object):
    def __init__(self, strand):
        self.strand = strand

    def hamming_distance(self, other_strand):
        length = min( len(self.strand), len (other_strand) )
        diff = 0

        for i in range(length):
            if not (self.strand[i] == other_strand[i]):
                diff += 1

        return diff
