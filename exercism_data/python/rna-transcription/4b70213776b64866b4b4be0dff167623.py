def to_rna(dna):
	rna_dict={'A': 'U' , 'C': 'G' , 'G': 'C' , 'T': 'A' }
	list1=list2=[]
	for i in dna:
		list1.append(i)

	for i in list1:

		if i in rna_dict.keys():
			list2.append(rna_dict[i])

		else:
			list2.append(i)

	rna=''.join(list2)		

	
	return rna

