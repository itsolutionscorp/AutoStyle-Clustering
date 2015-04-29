class DNA(object):
    def __init__(self, dnastring):
        self.dna = dnastring
    
    def dnatorna(self, dnanucleotide):
        mapping = {"A" : "U", "C" : "G", "G" : "C", "T" : "A"}
        return mapping[dnanucleotide]
    
    def to_rna(self):
        rna = map(self.dnatorna, self.dna)
        return ''.join(rna)
