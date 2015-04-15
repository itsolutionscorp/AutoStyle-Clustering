__author__ = 'Oniwa'


class DNA(object):
    """ DNA class to convert DNA into RNA
    """
    def __init__(self, dna):
        self.dna = dna

    def to_rna(self):
        """ to_rna converts a sequence of DNA into a sequence of RNA"""
        rna = []

        # Loops through string and converts the necleotides
        for char in self.dna:
            if char == 'G':
                rna.append('C')
            elif char == 'C':
                rna.append('G')
            elif char == 'T':
                rna.append('A')
            elif char == 'A':
                rna.append('U')

        # Turns the list into a string
        return ''.join(rna)
