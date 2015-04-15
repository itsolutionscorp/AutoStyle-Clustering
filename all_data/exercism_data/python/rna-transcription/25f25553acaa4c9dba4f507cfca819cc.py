# -*- coding: utf-8 -*-
def dna2rna(strand):
    """ Translates a given DNA string to the transcribed RNA string 
    corresponding to it.

    Both DNA and RNA strands are a sequence of nucleotides. The four 
    nucleotides found in DNA are adenine (**A**), cytosine (**C**), 
    guanine (**G**) and thymine (**T**). The four nucleotides found 
    in RNA are adenine (**A**), cytosine (**C**), guanine (**G**) 
    and uracil (**U**).

    Given a DNA strand, its transcribed RNA strand is formed by 
    replacing all occurrences of thymine with uracil.
    """
    return strand.replace("T", "U")

class DNA(object):
    """ Exercism.io wrapper for `dna2rna`. 

    It's unpythonic in both design (uses a class when a mere function 
    is enough) and naming (ALL_UPPER is for symbolic constants) but
    the tests won't path without it.
    """
    def __init__(self, strand):
        self.strand = strand

    def to_rna(self):
        return dna2rna(self.strand)
