import re

def to_rna(dna):
	return re.sub(r"[GTCA]", get_complement, dna)

def get_complement(letter):
	complements = { 'G' : 'C', 'C' : 'G', 'T' : 'A', 'A' : 'U' }
	return complements[letter.group(0)]
