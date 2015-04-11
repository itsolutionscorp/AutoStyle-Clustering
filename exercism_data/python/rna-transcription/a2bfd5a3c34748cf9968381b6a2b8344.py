#!/bin/python

def to_rna(dna):
	translate = {
		"G": "C",
		"C": "G",
		"T": "A",
		"A": "U",
	}
	return "".join([translate[strand] for strand in dna])
