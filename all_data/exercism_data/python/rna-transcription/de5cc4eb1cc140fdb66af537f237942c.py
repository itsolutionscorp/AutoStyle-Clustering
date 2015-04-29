#!/usr/bin/env python
 # -*- coding: utf-8 -*-

def to_rna(dna_strand):
	if not isinstance(dna_strand, "".__class__):
		raise ValueError("dna_strand must be a string")
	
	dna_to_rna = {"G" : "C","C" : "G", "T" : "A", "A" : "U"}
	rna_strand = "" 
	
	for dna_nucleotide in dna_strand:
		rna_strand = rna_strand + dna_to_rna[dna_nucleotide]
	
	return rna_strand
