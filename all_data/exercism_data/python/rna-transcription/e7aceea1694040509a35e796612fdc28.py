###################################
# Function converts string of dna characters to rna characters
# inputs: string
# returns: string with conversions of
#		`G` -> `C`
# 		`C` -> `G`
# 		`T` -> `A`
# 		`A` -> `U`
def to_rna(dna):
	#convert string to list
	dna = list(dna)
	rna = ""
	
	#convert each letter to appropriate rna character
	for i in dna:
		if i ==  "G":
			rna += "C"
		elif i ==  "C":
			rna += "G"
		elif i ==  "T":
			rna += "A"
		elif i ==  "A":
			rna += "U"
	
	return rna
