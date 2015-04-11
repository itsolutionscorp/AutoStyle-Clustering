class DNA:
    def __init__(self, strand):
        self.strand = strand

    def hamming_distance(self, other_strand):
        zipped = zip(self.strand, other_strand)
        return sum([i != j for i, j in zipped])
