class DNA(object):
    COMPLEMENTS = {'G': 'C', 'C': 'G', 'T': 'A', 'A' : 'U'}
    
    def __init__(self, strand):
        self.__strand = strand
    
    def to_rna(self):
        return "".join(map(lambda nucleotide : DNA.COMPLEMENTS[nucleotide], self.__strand))
    
