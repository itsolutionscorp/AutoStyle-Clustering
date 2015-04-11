"""
DNA to RNA translator.

Translate a given DNA string to the transcribed RNA string corresponding to it.
"""

class DNA(object):
    """ Representation of a DNA string. """

    def __init__(self, nucleotides):
        self.nucleotides = nucleotides

    def to_rna(self):
        """
        Translate DNA to RSA.

        RNA strand is formed by replacing all occurrences of thymine with
        uracil.
        """
        return self.nucleotides.replace('T', 'U')
