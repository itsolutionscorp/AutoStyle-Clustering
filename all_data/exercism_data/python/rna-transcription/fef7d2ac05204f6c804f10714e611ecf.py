class DNA(object):
    TRANSLATION = str.maketrans('GCTA', 'CGAU')
    
    def __init__(self, strand):
        self.__strand = strand
    
    def to_rna(self):
        return self.__strand.translate(DNA.TRANSLATION)
    
