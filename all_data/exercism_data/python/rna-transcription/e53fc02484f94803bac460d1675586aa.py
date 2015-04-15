__author__ = 'dmwoods'
from string import maketrans


def to_rna(nucleotides):
    return nucleotides.translate(maketrans("GCTA", "CGAU"))
