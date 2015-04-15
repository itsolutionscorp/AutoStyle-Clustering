#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from __future__ import print_function

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
	rna = rna.replace('A', 'U')
	rna = rna.replace('T', 'A')

	# use temporary lower-case 'c' to differentiate between DNA's and RNA's 'C'
	rna = rna.replace('C', 'c')
	rna = rna.replace('G', 'C')

	# re-replace temporay lower-case 'c' to RNA's 'G'
	rna = rna.replace('c', 'G')


	return rna
