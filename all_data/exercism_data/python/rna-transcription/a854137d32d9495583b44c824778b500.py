"""
Returns a DNA strand's RNA complement.
Written by Bede Kelly for Exercism.
"""

import string
__author__ = "Bede Kelly"

# Create a string translation table. Only works for single-char methods.
trans_table = string.maketrans("GCTA", "CGAU")

def to_rna(dna):
	"""Returns a DNA strand's RNA complement."""
	return dna.translate(trans_table)
