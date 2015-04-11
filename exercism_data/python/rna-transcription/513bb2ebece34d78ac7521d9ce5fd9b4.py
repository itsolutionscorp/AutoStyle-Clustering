# -*- coding: utf-8 -*-


def to_rna(DNAstrand):
    """
    Given a DNA strand, return its RNA complement
    * `G` -> `C`
    * `C` -> `G`
    * `T` -> `A`
    * `A` -> `U`
    """
    RNAstrand = ''
    for nucleotide in DNAstrand:
        if nucleotide == 'G':
            RNAstrand += 'C'
        if nucleotide == 'C':
            RNAstrand += 'G'
        if nucleotide == 'T':
            RNAstrand += 'A'
        if nucleotide == 'A':
            RNAstrand += 'U'
    return RNAstrand
