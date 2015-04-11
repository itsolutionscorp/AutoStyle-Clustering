def Allergies(n):
	allergies_indices = []
	n_to_bin = bin(n)
	n_to_bin = n_to_bin[::-1]
	for i in xrange(len(n_to_bin) - 2):
		if (n_to_bin[i] == '1'):
			allergies_indices.append(2 ** i)
	return allergies_indices
	
def is_allergic_to(n):
	allergy_indices = Allergies(n)
	allergy_names = []
	for x in allergy_indices:
		if (x <= 128):
			allergy_names.append(allergy_dict[x])
	return allergy_names	
	
allergy_dict = {1:'eggs',2:'peanuts',4:'shellfish',8:'strawberries',16:'tomatoes',
32:'chocolate',64:'pollen',128:'cats'}
			
			
