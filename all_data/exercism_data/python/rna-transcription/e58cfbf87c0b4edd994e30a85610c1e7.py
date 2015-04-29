__author__ = 'benlarue'

from string import maketrans


def to_rna(dna_string):
	trans = maketrans("GCTA", "CGAU")
	return dna_string.translate(trans)
