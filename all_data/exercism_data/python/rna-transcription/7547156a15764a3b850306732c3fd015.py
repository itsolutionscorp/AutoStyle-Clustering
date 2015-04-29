#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Submission file for the python rna-transcription exercise.
#
# v3: Use global constant for translation table.
# v2: Change to string.maketrans().
# v1: Using string.replace().

# translation table to translate the left characters to the right ones
TRANS = str.maketrans('GCTA', 'CGAU')

def to_rna(dna):
	"""
	Given a DNA strand, returns its RNA complement (per RNA transcription).

	Given a DNA strand, its transcribed RNA strand is formed by replacing
	each nucleotide with its complement:

	* `G` -> `C`
	* `C` -> `G`
	* `T` -> `A`
	* `A` -> `U`
	"""

	rna = dna.upper()
	rna = rna.translate(TRANS)

	return rna
