import re

def to_rna(dna):
	pattern = re.compile('[^ACGTacgt]')
	converter = {
		"G": "C",
		"C": "G",
		"T": "A",
		"A": "U"
	}
	if pattern.search(dna):
		raise "Warning: sequence contains illegal characters"

	data = dna.upper()

	rna = ""
	for letter in data:
		rna += converter[letter]

	return rna
