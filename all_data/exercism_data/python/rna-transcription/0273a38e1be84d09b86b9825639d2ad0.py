__author__ = 'shammas'
import string


class DNA:
    """
    A simple container that holds the DNA sequence and a function to translate the DNA sequence to a RNA sequence
    """

    dna = ''
    transToRNA = string.maketrans('GCTA', 'CGAU')

    def __init__(self, dna):
        self.dna = dna

    def to_rna(self):
        return string.translate(self.dna, self.transToRNA)
