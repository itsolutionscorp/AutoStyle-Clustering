class DNA:

    def __init__(self, strand):
        if strand is '':
            self.nucleotide = None
            self.next = None
        else:
            self.nucleotide = strand[0]
            self.next = DNA(strand[1:])

    def to_rna(self):
        if self.nucleotide is None:
            return ''
        return {
          # DNA:RNA #
            'C':'C',
            'G':'G',
            'A':'A',
            'T':'U'}[self.nucleotide] + self.next.to_rna()
            
