dna_to_rna_mapping = {	"G" : "C",
						"C" : "G",
						"T" : "A",
						"A" : "U"}
						
def to_rna(dna_sequence):
	rna_output = ""
	for nucleotides in dna_sequence:
		rna_output += dna_to_rna_mapping[nucleotides]
	return rna_output
