def to_rna(dna_strand):
	# Use a dictionary for the conversion
	rna_conversion_dictionary = {"G":"C", "C":"G", "T": "A", "A": "U"}
	rnd_strand = ""
	for strand in dna_strand:
		rnd_strand += rna_conversion_dictionary[strand]

	return rnd_strand
