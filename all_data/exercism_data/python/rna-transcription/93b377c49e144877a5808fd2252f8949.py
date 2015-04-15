from string import maketrans

class DNA:

    def __init__(self,strand):

        self.strand=strand

    def to_rna(self):

        transcr=maketrans("GCTA","CGAU")
        return self.strand.translate(transcr) 
