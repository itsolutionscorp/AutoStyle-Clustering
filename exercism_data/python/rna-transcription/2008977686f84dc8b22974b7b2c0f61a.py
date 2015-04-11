def to_rna(dna):
	to_rna = {"G":"C", "C":"G", "T":"A", "A":"U"}
	return "".join([to_rna[letter] for letter in dna])
