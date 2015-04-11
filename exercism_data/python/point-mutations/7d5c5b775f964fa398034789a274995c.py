class DNA:
    def __init__(self, strand):
        self.strand = strand
        return

    def hamming_distance(self, other_strand):
        return sum([ a != b for a, b in
                     zip(self.strand, other_strand)])

    pass
