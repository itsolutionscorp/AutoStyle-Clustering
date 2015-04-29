from string import maketrans

def to_rna(dna):
	table = maketrans("ATCG", "UAGC") #Create a table for translate() to use
	return dna.translate(table)
