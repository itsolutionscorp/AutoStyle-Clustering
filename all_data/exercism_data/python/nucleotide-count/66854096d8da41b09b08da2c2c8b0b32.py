class DNA(object):

    def __init__(self,strand):
        self.strand=strand

    def count(self,nuc):
        if nuc not in "ATCG" + "U":
            raise ValueError("{0} is not a nucleotide.".format(nuc) )
        return self.strand.count( nuc )

    def nucleotide_counts(self):
        return dict( zip( "ATCG", [self.strand.count(c) for c in "ATCG"] ) )

        
