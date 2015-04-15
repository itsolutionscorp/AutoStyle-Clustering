def to_rna(dna_seq):
	result = ""

	for c in dna_seq:
		result += to_rna_single(c)

	return result

def to_rna_single(dna_seq):
	if dna_seq == "G":
		return "C"
	elif dna_seq == "C":
		return "G"
	elif dna_seq == "T":
		return "A"
	elif dna_seq == "A":
		return "U"
