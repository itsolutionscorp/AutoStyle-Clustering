class DNA(object):
    def __init__(self, strand):
        self.strand = strand

    def hamming_distance(self, other_strand):
        return 
          count(
            filter(
                lambda (x,y): x != y,
                zip(self.strand, otherstrand) )
