class DNA:
    def __init__(self, strand):
        self.strand = strand
        self.uracils = {'U': 0}
        
    def count(self, ade=''):
        if not ade or ade in self.uracils.keys():
            return 0
        else:
            return self.nucleotide_counts(ade)
    
    def nucleotide_counts(self, ade=''):
        nucleotides = {'A': 0, 'T': 0, 'C': 0, 'G': 0}
        uracils = {'U':0}

        for char in list(self.strand):
            if char in nucleotides:
                nucleotides[char] += 1
            else:
                pass
        if ade:
            try:
                return nucleotides[ade]
            except KeyError:
                raise ValueError("%s is not a nucleotide." %ade)
        else:
            return nucleotides
