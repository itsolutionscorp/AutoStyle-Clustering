import re

def to_rna(dna):
	rna = re.sub(r"[GTCA]", get_complement, dna)
	return rna

def get_complement(letter):
	complements = { 'G' : 'C', 'C' : 'G', 'T' : 'A', 'A' : 'U' }
	return complements[letter.group(0)]
