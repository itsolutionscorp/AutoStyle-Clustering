#!/usr/bin/env python3
# -*- coding: utf-8 -*-

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

	# translation table to translate the left characters to the right ones
	trans = str.maketrans('GCTA', 'CGAU')

	rna = dna.upper()
	rna = rna.translate(trans)

	return rna
