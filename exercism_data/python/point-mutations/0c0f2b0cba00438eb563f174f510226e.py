class DNA(object):
    def __init__(self, strand):
        self.strand = strand

    def hamming_distance(self, other_dna_strand):
        return sum(x != y for (x, y) in zip(self.strand, other_dna_strand))
