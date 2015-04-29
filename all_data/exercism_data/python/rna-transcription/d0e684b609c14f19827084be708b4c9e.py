import re

def to_rna(dna):
	pattern = re.compile('[^ACGTacgt]')
	if pattern.search(dna):
		raise "Warning: sequence contains illegal characters"

	data = dna.upper()

	rna = ""
	for letter in data:
		if letter == "G":
			rna += "C"
		elif letter == "C":
			rna += "G"
		elif letter == "T":
			rna += "A"
		elif letter == "A":
			rna += "U"

	return rna
