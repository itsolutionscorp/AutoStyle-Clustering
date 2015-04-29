def to_rna(dna):
	rna_dict = {'A': 'U' , 'C': 'G' , 'G': 'C' , 'T': 'A' }
	list1 = []															
	for i in dna:
		list1.append(rna_dict[i])
	rna = ''.join(list1)		
	return rna

