class DNA(object):
    def __init__(self, dnastring):
        self.dna = dnastring
        
    def to_rna(self):
        mapping = {"A" : "U", "C" : "G", "G" : "C", "T" : "A"}
        rna = map(lambda x: mapping[x], self.dna)
        return ''.join(rna)
