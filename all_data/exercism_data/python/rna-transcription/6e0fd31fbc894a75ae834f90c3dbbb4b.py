from string import maketrans


class DNA(object):
    """ DNA (Deoxyribonucleic acid) class """
    def __init__(self, nucleotides):
        """ Initialize class

        @param nucleotides: a sequence of DNA nucleotides
        """
        self.nucleotides = nucleotides
        
    def to_rna(self):
        """ Translate from DNA to RHA sequences

        @return: RNA sequence
        """
        return self.nucleotides.translate(maketrans("ACGT", "ACGU"))
