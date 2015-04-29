import re


class DNA():

    def __init__(self, dna_string):
        """Save DNA string,
        Raise execption if check that DNA made of base pairs"""

        if self._is_a_dna_string(dna_string) is False:
            raise Exception("Not a DNA string")

        self.dna_string = dna_string

    def to_rna(self, correct_orientation=False):
        """Returns the transcribed RNA string.
        In biological systems transcribed RNA is the reverse of
        the DNA string. An option is provided to return the correct
        orientation of the returned RNA string."""

        rna_string = ''
        for base in self.dna_string:
            if(base is 'A' or base is 'a'):
                rna_string += 'U'
            elif(base is 'T' or base is 't'):
                rna_string += 'A'
            elif(base is 'G' or base is 'g'):
                rna_string += 'C'
            elif(base is 'C' or base is 'c'):
                rna_string += 'G'

        if correct_orientation is False:
            return rna_string
        else:
            return self._reverse_orientation(rna_string)

    def _reverse_orientation(self, na_string):
        """Reverses the orientation of the Nucleic Acid (na_string)"""
        return na_string[::-1]

    def _is_a_dna_string(self, test_string):
        """Checks that test_string is a string of DNA bases"""

        if(re.search(r'[^ATGCatgc]', test_string)):
            return False
        else:
            return True
