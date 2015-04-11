class DNA(object):
    """ Represents a DNA strand. """

    def __init__(self, dna_strand):
        self.dna_strand = dna_strand

    def to_rna(self):
        """ Translates this DNA to RNA. """

        return self.dna_strand.replace("T", "U")
