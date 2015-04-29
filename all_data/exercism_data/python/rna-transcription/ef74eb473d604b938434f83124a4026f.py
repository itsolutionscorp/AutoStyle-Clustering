__author__ = 'dwende'

from string import maketrans

transtable = maketrans("GCTA", "CGAU")

def to_rna(dna):
    return dna.translate(transtable)

