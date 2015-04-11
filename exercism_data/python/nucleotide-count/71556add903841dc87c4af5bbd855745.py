class DNA:
    def __init__(self,strand):
        self.strand = strand

        self.nucleotides = dict()

        for nuc in [ 'A', 'T', 'C', 'G' ]:
            self.nucleotides[nuc] = strand.count(nuc)

    def count(self,n):
        if n in [ 'A', 'T', 'C', 'G', 'U' ]:
            return self.nucleotides[n] if self.nucleotides.has_key(n) else 0

        raise( ValueError( n + ' is not a nucleotide.' ) )

    def nucleotide_counts(self):
        return self.nucleotides
