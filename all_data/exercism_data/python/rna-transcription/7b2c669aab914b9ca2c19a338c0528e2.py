class DNA:
    transcriptons = {'C': 'G', 'G': 'C', 'T': 'A', 'A': 'U'}

    def __init__(self, strand):
        self.strand = strand;
    def to_rna(self):
        rnaStrand = ''
        for dnaChar in self.strand:
            rnaStrand = rnaStrand + self.transcriptons[dnaChar]
        return rnaStrand
        
