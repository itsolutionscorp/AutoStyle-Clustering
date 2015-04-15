rna_dict = {'A': 'U' , 'C': 'G' , 'G': 'C' , 'T': 'A' }
def to_rna(dna):
	rna_list = []															
	for i in dna:
		rna_list.append(rna_dict[i])
	rna = ''.join(rna_list)		
	return rna

