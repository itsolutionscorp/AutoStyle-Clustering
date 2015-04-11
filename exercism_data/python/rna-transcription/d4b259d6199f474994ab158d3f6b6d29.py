#!/usr/bin/env python
# -*- coding: utf-8 -*-

def to_rna(dna):
	rna = ''
	table = {'G':'C','C':'G','T':'A','A':'U'}
	for i in range(len(dna)):
		rna += table[dna[i]]
	return rna
