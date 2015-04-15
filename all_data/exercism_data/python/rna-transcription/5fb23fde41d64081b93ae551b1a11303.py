#!/usr/bin/python3 -V
# -*- coding: utf-8 -*-

def to_rna(dna):
	return dna.translate(dna.maketrans('GCTA', 'CGAU'))
