#!/usr/bin/env python3

def to_rna(instr):
	trans = str.maketrans("GCTA","CGAU")
	return instr.translate(trans)
