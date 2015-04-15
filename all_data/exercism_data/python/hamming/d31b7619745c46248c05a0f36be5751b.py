#!/usr/bin/env python

def hamming(seq1, seq2):
	return sum(p[0] != p[1] for p in map(None, seq1, seq2))
	
	
		
